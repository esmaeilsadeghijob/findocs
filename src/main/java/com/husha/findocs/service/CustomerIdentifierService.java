package com.husha.findocs.service;

import com.husha.findocs.model.CustomerIdentifier;
import com.husha.findocs.repository.CustomerIdentifierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerIdentifierService {

    private final CustomerIdentifierRepository repository;

    public List<CustomerIdentifier> getAll() {
        return repository.findAll();
    }

    public CustomerIdentifier create(String code, String description) {
        if (repository.existsByCode(code))
            throw new RuntimeException("این شناسه قبلاً ثبت شده");

        var identifier = CustomerIdentifier.builder()
                .id(UUID.randomUUID())
                .code(code)
                .description(description)
                .createdAt(Instant.now())
                .build();

        return repository.save(identifier);
    }

    public CustomerIdentifier getByCode(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("شناسه یافت نشد"));
    }
}
