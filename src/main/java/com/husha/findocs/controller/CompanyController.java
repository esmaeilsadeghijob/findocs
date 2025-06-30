package com.husha.findocs.controller;

import com.husha.findocs.dto.CompanyDto;
import com.husha.findocs.model.User;
import com.husha.findocs.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {

    private final CompanyService service;

    @PostMapping
    public ResponseEntity<CompanyDto> create(@RequestBody CompanyDto dto,
                                             @AuthenticationPrincipal User user) {
        return ResponseEntity.status(201).body(service.create(dto, user.getUsername()));
    }

    @GetMapping
    public List<CompanyDto> all() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> update(@PathVariable UUID id,
                                             @RequestBody CompanyDto dto,
                                             @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.update(id, dto, user.getUsername()));
    }

}
