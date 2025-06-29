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
                .orElseGet(() -> {
                    Attachment att = new Attachment();
                    att.setId(null);
                    att.setDocumentId(documentId);
                    att.setCreatedBy(username);
                    att.setAttachments(new ArrayList<>());
                    return att;
                });

        attachment.getAttachments().add(createMeta(file, username, description, nature));

        attachmentRepo.save(attachment);
    }

    public void uploadFiles(UUID documentId, MultipartFile[] files, String[] descriptions, String username) {
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String desc = (descriptions != null && i < descriptions.length) ? descriptions[i] : null;
            try {
                addAttachment(documentId, file, username, desc, null);
            } catch (IOException e) {
                throw new RuntimeException("خطا در بارگذاری فایل: " + file.getOriginalFilename());
            }
        }
    }

    private FileMeta createMeta(MultipartFile file, String username, String description, String nature) throws IOException {
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

        return meta;
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
