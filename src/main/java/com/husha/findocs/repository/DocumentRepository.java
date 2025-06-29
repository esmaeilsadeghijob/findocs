package com.husha.findocs.repository;

import com.husha.findocs.model.Document;
import com.husha.findocs.model.DocumentStatus;
import com.husha.findocs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
    List<Document> findByUser(User user);

//    List<Document> findByUserAndIsActiveTrue(User user);

//    List<Document> findByClientId(UUID clientId);

//    List<Document> findByClientIdAndIsActiveTrue(UUID clientId);

//    List<Document> findByStatus(DocumentStatus status);

//    List<Document> findByStatusAndIsActiveTrue(DocumentStatus status);
}
