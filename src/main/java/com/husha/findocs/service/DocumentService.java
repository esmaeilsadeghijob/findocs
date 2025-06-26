package com.husha.findocs.service;

import com.husha.findocs.dto.DocumentDto;
import com.husha.findocs.model.Client;
import com.husha.findocs.model.Document;
import com.husha.findocs.model.Project;
import com.husha.findocs.repository.ClientRepository;
import com.husha.findocs.repository.DocumentRepository;
import com.husha.findocs.repository.ProjectRepository;
import com.husha.findocs.repository.UserRepository;
import com.husha.findocs.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final ClientRepository clientRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Document createDocument(DocumentDto dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("کاربر یافت نشد"));

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("مشتری یافت نشد"));

        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("پروژه یافت نشد"));

        Document doc = new Document();
        doc.setId(UUID.randomUUID());
        doc.setClient(client);
        doc.setProject(project);
        doc.setUser(user);
        doc.setDocumentNumber(dto.getDocumentNumber());
        doc.setFiscalYear(dto.getFiscalYear());
        doc.setDocumentDate(dto.getDocumentDate());
        doc.setDescription(dto.getDescription());
        doc.setNature(dto.getNature());
        doc.setCreatedAt(Instant.now());

        return documentRepository.save(doc);
    }

    public List<Document> getDocumentsForUser(User user) {
        if (user.getRole().getName().equals("ROLE_ADMIN")) {
            return documentRepository.findAll();
        }
        return documentRepository.findByUser(user);
    }
}
