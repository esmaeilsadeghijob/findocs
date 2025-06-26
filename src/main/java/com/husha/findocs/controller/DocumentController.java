package com.husha.findocs.controller;

import com.husha.findocs.dto.DocumentDto;
import com.husha.findocs.model.Document;
import com.husha.findocs.model.User;
import com.husha.findocs.repository.UserRepository;
import com.husha.findocs.service.DocumentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentController {

    private final DocumentService documentService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Document> create(@RequestBody DocumentDto dto,
                                           @AuthenticationPrincipal User user) {
        Document doc = documentService.createDocument(dto, user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(doc);
    }

    @GetMapping
    public ResponseEntity<List<Document>> getAll(@AuthenticationPrincipal User user) {
        List<Document> docs = documentService.getDocumentsForUser(user);
        return ResponseEntity.ok(docs);
    }
}
