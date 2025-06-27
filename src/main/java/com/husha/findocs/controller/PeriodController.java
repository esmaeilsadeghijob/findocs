package com.husha.findocs.controller;

import com.husha.findocs.model.Period;
import com.husha.findocs.service.PeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/periods")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PeriodController {

    private final PeriodService periodService;

    @GetMapping
    public ResponseEntity<List<Period>> getAll() {
        return ResponseEntity.ok(periodService.getAll());
    }

    @PostMapping
    public ResponseEntity<Period> create(@RequestBody Period period) {
        return ResponseEntity.ok(periodService.create(period));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Period> update(@PathVariable UUID id, @RequestBody Period period) {
        return ResponseEntity.ok(periodService.update(id, period));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        periodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
