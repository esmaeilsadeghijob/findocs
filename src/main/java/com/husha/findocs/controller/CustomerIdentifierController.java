package com.husha.findocs.controller;

import com.husha.findocs.model.CustomerIdentifier;
import com.husha.findocs.service.CustomerIdentifierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/identifiers")
@RequiredArgsConstructor
public class CustomerIdentifierController {

    private final CustomerIdentifierService service;

    @PostMapping
    public ResponseEntity<CustomerIdentifier> create(@RequestBody Map<String, String> body) {
        String code = body.get("code");
        String description = body.get("description");
        return ResponseEntity.ok(service.create(code, description));
    }

    @GetMapping
    public List<CustomerIdentifier> getAll() {
        return service.getAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<CustomerIdentifier> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(service.getByCode(code));
    }
}
