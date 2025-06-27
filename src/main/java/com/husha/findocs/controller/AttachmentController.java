package com.husha.findocs.controller;

import com.husha.findocs.document.Attachment.FileMeta;
import com.husha.findocs.model.User;
import com.husha.findocs.service.AttachmentService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/attachments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/{documentId}")
    public ResponseEntity<String> uploadAttachment(@PathVariable UUID documentId,
                                                   @RequestParam("file") MultipartFile file,
                                                   @RequestParam(required = false) String description,
                                                   @RequestParam(required = false) String nature,
                                                   @AuthenticationPrincipal User user) {
        try {
            attachmentService.addAttachment(documentId, file, user.getUsername(), description, nature);
            return ResponseEntity.ok("فایل با موفقیت اضافه شد");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("خطا در بارگذاری فایل");
        }
    }

    @PostMapping("/{documentId}/attachments")
    public ResponseEntity<?> uploadAttachments(@PathVariable UUID documentId,
                                               @RequestParam("files") MultipartFile[] files,
                                               @AuthenticationPrincipal User user) {
        attachmentService.uploadFiles(documentId, files, user.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<List<FileMeta>> getAttachments(@PathVariable UUID documentId) {
        return attachmentService.getAttachments(documentId)
                .map(attachment -> ResponseEntity.ok(attachment.getAttachments()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{documentId}/{fileId}")
    public ResponseEntity<Void> deleteFile(@PathVariable UUID documentId,
                                           @PathVariable String fileId) {
        boolean deleted = attachmentService.deleteAttachment(documentId, fileId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{documentId}/preview/{fileId}")
    public ResponseEntity<byte[]> previewFile(@PathVariable UUID documentId,
                                              @PathVariable String fileId) {
        Optional<FileMeta> fileOpt = attachmentService.getFileMeta(documentId, fileId);
        return fileOpt.map(file -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + file.getFileName())
                        .header(HttpHeaders.CONTENT_TYPE, file.getMimeType())
                        .body(file.getFileData()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{documentId}/file/{fileId}")
    public ResponseEntity<byte[]> downloadRawFile(@PathVariable UUID documentId,
                                                  @PathVariable String fileId) {
        Optional<FileMeta> fileOpt = attachmentService.getFileMeta(documentId, fileId);
        return fileOpt.map(file -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(file.getMimeType()));
            headers.setContentDisposition(ContentDisposition.inline().filename(file.getFileName()).build());
            return new ResponseEntity<>(file.getFileData(), headers, HttpStatus.OK);
        }).orElse(ResponseEntity.notFound().build());
    }

    // ⬇ مسیر عمومی برای پیش‌نمایش فایل در embed/image بدون نیاز به احراز هویت
    @GetMapping("/public/{documentId}/file/{fileId}")
    @PermitAll
    public ResponseEntity<byte[]> viewFilePublic(@PathVariable UUID documentId,
                                                 @PathVariable String fileId) {
        Optional<FileMeta> fileOpt = attachmentService.getFileMeta(documentId, fileId);
        return fileOpt.map(file -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(file.getMimeType()));
            headers.setContentDisposition(ContentDisposition.inline().filename(file.getFileName()).build());
            return new ResponseEntity<>(file.getFileData(), headers, HttpStatus.OK);
        }).orElse(ResponseEntity.notFound().build());
    }

}
