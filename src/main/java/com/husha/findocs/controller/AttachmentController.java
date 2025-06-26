package com.husha.findocs.controller;

import com.husha.findocs.document.Attachment;
import com.husha.findocs.model.User;
import com.husha.findocs.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/{documentId}")
    public ResponseEntity<Attachment> getAttachments(@PathVariable UUID documentId) {
        return attachmentService.getAttachments(documentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

