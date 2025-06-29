package com.husha.findocs.controller;

import com.husha.findocs.dto.DocumentDto;
import com.husha.findocs.model.Document;
import com.husha.findocs.model.User;
import com.husha.findocs.service.DocumentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentDto> create(@RequestBody DocumentDto dto,
                                              @AuthenticationPrincipal User user) {
        Document saved = documentService.createDocument(dto, user.getUsername());
        return ResponseEntity.status(201).body(DocumentDto.from(saved));
    }

    @GetMapping
    public ResponseEntity<List<DocumentDto>> getAll(@AuthenticationPrincipal User user) {
        List<DocumentDto> docs = documentService.getDocumentsForUser(user);
        return ResponseEntity.ok(docs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable UUID id) {
        documentService.deactivateDocument(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DocumentDto> changeStatus(@PathVariable UUID id) {
        Document updated = documentService.advanceStatus(id);
        return ResponseEntity.ok(DocumentDto.from(updated));
    }
}
