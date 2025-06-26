package com.husha.findocs.service;

import com.husha.findocs.model.ServiceEntity;
import com.husha.findocs.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SService {

    private final ServiceRepository serviceRepository;

    public List<ServiceEntity> getAll() {
        return serviceRepository.findAll();
    }

    public ServiceEntity getById(UUID id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("سرویس یافت نشد"));
    }

    public ServiceEntity create(ServiceEntity service) {
        service.setId(UUID.randomUUID());
        service.setCreatedAt(Instant.now());
        return serviceRepository.save(service);
    }

    public ServiceEntity update(UUID id, ServiceEntity updated) {
        ServiceEntity existing = getById(id);
        existing.setName(updated.getName());
        return serviceRepository.save(existing);
    }

    public void delete(UUID id) {
        serviceRepository.deleteById(id);
    }
}
