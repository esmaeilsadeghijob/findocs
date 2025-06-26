package com.husha.findocs.service;

import com.husha.findocs.document.Attachment;
import com.husha.findocs.document.Attachment.FileMeta;
import com.husha.findocs.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepo;

    public void addAttachment(UUID documentId, MultipartFile file, String username,
                              String description, String nature) throws IOException {

        Attachment attachment = attachmentRepo.findByDocumentId(documentId)
                .orElse(new Attachment(null, documentId, new ArrayList<>()));

        String originalName = file.getOriginalFilename();
        String ext = (originalName != null && originalName.contains("."))
                ? originalName.substring(originalName.lastIndexOf('.') + 1)
                : "unknown";

        FileMeta meta = new FileMeta();
        meta.setId(UUID.randomUUID().toString());
        meta.setFileName(originalName);
        meta.setUploadedAt(Instant.now());
        meta.setUploadedBy(username);
        meta.setDescription(description);
        meta.setNature(nature);
        meta.setMimeType(file.getContentType());
        meta.setExtension(ext.toLowerCase());
        meta.setFileData(file.getBytes());

        attachment.getAttachments().add(meta);
        attachmentRepo.save(attachment);
    }

    public Optional<Attachment> getAttachments(UUID documentId) {
        return attachmentRepo.findByDocumentId(documentId);
    }

    public boolean deleteAttachment(UUID documentId, String fileId) {
        Optional<Attachment> optional = attachmentRepo.findByDocumentId(documentId);
        if (optional.isPresent()) {
            Attachment attachment = optional.get();
            boolean removed = attachment.getAttachments().removeIf(a -> fileId.equals(a.getId()));
            if (removed) {
                attachmentRepo.save(attachment);
                return true;
            }
        }
        return false;
    }

    public Optional<FileMeta> getFileMeta(UUID documentId, String fileId) {
        return attachmentRepo.findByDocumentId(documentId)
                .flatMap(att -> att.getAttachments().stream()
                        .filter(a -> fileId.equals(a.getId()))
                        .findFirst());
    }
}
