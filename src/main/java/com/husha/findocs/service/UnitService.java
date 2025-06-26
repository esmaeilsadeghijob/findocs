package com.husha.findocs.service;

import com.husha.findocs.model.Unit;
import com.husha.findocs.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    public List<Unit> getAll() {
        return unitRepository.findAll();
    }

    public Unit getById(UUID id) {
        return unitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("واحد یافت نشد"));
    }

    public Unit create(Unit unit) {
        unit.setId(UUID.randomUUID());
        unit.setCreatedAt(Instant.now());
        return unitRepository.save(unit);
    }

    public Unit update(UUID id, Unit updated) {
        Unit existing = getById(id);
        existing.setName(updated.getName());
        return unitRepository.save(existing);
    }

    public void delete(UUID id) {
        unitRepository.deleteById(id);
    }
}
