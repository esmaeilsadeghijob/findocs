package com.husha.findocs.repository;

import com.husha.findocs.document.Attachment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttachmentRepository extends MongoRepository<Attachment, String> {
    Optional<Attachment> findByDocumentId(UUID documentId);
}


