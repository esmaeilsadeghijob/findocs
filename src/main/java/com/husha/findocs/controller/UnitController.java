package com.husha.findocs.controller;

import com.husha.findocs.model.Unit;
import com.husha.findocs.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    public ResponseEntity<List<Unit>> getAll() {
        return ResponseEntity.ok(unitService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unit> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(unitService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Unit> create(@RequestBody Unit unit) {
        return ResponseEntity.ok(unitService.create(unit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unit> update(@PathVariable UUID id, @RequestBody Unit unit) {
        return ResponseEntity.ok(unitService.update(id, unit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        unitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
