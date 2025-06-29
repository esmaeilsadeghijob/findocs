package com.husha.findocs.service;

import com.husha.findocs.dto.DocumentDto;
import com.husha.findocs.model.*;
import com.husha.findocs.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final ClientRepository clientRepository;
    private final UnitRepository unitRepository;
    private final PeriodRepository periodRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    @Transactional
    public Document createDocument(DocumentDto dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("کاربر یافت نشد"));

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("مشتری یافت نشد"));

        Unit unit = unitRepository.fetchWithAllFields(dto.getUnitId())
                .orElseThrow(() -> new RuntimeException("واحد یافت نشد"));

        Period period = periodRepository.findById(dto.getPeriodId())
                .orElseThrow(() -> new RuntimeException("سال مالی یافت نشد"));

        ServiceEntity service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("سرویس یافت نشد"));

        Document doc = new Document();
        doc.setId(UUID.randomUUID());
        doc.setClient(client);
        doc.setUnit(unit);
        doc.setPeriod(period);
        doc.setService(service);
        doc.setUser(user);
        doc.setDocumentNumber(dto.getDocumentNumber());
        doc.setDocumentDate(dto.getDocumentDate());
        doc.setDescription(dto.getDescription());
        doc.setNature(dto.getNature());
        doc.setStatus(DocumentStatus.DRAFT);
        doc.setActive(true);
        doc.setCreatedAt(Instant.now());
        doc.setCreatedBy(username);

        return documentRepository.save(doc);
    }

    public List<DocumentDto> getDocumentsForUser(User user) {
        List<Document> result;
        if ("ROLE_ADMIN".equals(user.getRole().getName())) {
            result = documentRepository.findAll();
        } else {
            result = documentRepository.findByUser(user);
        }

        return result.stream()
                .filter(Document::isActive)
                .map(DocumentDto::from)
                .toList();
    }

    public void deactivateDocument(UUID documentId) {
        Document doc = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("سند یافت نشد"));
        doc.setActive(false);
        documentRepository.save(doc);
    }

    public Document advanceStatus(UUID id) {
        Document doc = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("سند یافت نشد"));

        if (doc.getStatus() == DocumentStatus.FINALIZED) {
            return doc; // تغییری اعمال نمی‌کنیم
        }

        if (doc.getStatus() == DocumentStatus.DRAFT) {
            doc.setStatus(DocumentStatus.SUBMITTED);
        } else if (doc.getStatus() == DocumentStatus.SUBMITTED) {
            doc.setStatus(DocumentStatus.FINALIZED);
        }

        return documentRepository.save(doc);
    }
}
