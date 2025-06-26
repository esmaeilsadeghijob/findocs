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

        FileMeta meta = new FileMeta();
        meta.setFileName(file.getOriginalFilename());
        meta.setUploadedAt(Instant.now());
        meta.setUploadedBy(username);
        meta.setDescription(description);
        meta.setNature(nature);
        meta.setMimeType(file.getContentType());
        meta.setFileData(file.getBytes());

        attachment.getAttachments().add(meta);
        attachmentRepo.save(attachment);
    }

    public Optional<Attachment> getAttachments(UUID documentId) {
        return attachmentRepo.findByDocumentId(documentId);
    }
}
