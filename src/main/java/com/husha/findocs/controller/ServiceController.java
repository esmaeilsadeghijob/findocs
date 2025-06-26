package com.husha.findocs.controller;

import com.husha.findocs.model.ServiceEntity;
import com.husha.findocs.service.SService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final SService sservice;

    @GetMapping
    public ResponseEntity<List<ServiceEntity>> getAll() {
        return ResponseEntity.ok(sservice.getAll());
    }

    @PostMapping
    public ResponseEntity<ServiceEntity> create(@RequestBody ServiceEntity service) {
        return ResponseEntity.ok(sservice.create(service));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntity> update(@PathVariable UUID id, @RequestBody ServiceEntity service) {
        return ResponseEntity.ok(sservice.update(id, service));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        sservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
