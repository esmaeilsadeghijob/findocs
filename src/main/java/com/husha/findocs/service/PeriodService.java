package com.husha.findocs.service;

import com.husha.findocs.model.Period;
import com.husha.findocs.repository.PeriodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PeriodService {

    private final PeriodRepository periodRepository;

    public List<Period> getAll() {
        return periodRepository.findAll();
    }

    public Period getById(UUID id) {
        return periodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("دوره یافت نشد"));
    }

    public Period create(Period period) {
        period.setId(UUID.randomUUID());
        period.setCreatedAt(Instant.now());
        return periodRepository.save(period);
    }

    public Period update(UUID id, Period updated) {
        Period existing = getById(id);
        existing.setFiscalYear(updated.getFiscalYear());
        return periodRepository.save(existing);
    }

    public void delete(UUID id) {
        periodRepository.deleteById(id);
    }
}
