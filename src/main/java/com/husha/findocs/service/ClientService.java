package com.husha.findocs.service;

import com.husha.findocs.dto.ClientDto;
import com.husha.findocs.model.Client;
import com.husha.findocs.model.ServiceEntity;
import com.husha.findocs.model.Unit;
import com.husha.findocs.repository.ClientRepository;
import com.husha.findocs.repository.CustomerIdentifierRepository;
import com.husha.findocs.repository.ServiceRepository;
import com.husha.findocs.repository.UnitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UnitRepository unitRepository;
    private final ServiceRepository serviceRepository;
    private final CustomerIdentifierRepository identifierRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("مشتری یافت نشد"));
    }

    @Transactional
    public Client create(Client client) {
        client.setId(UUID.randomUUID());
        client.setCreatedAt(Instant.now());
        client.setActive(true);
        return clientRepository.save(client);
    }

    @Transactional
    public Client create(ClientDto dto) {
        Unit unit = unitRepository.findById(dto.getUnitId())
                .orElseThrow(() -> new RuntimeException("واحد یافت نشد"));

        ServiceEntity service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("سرویس یافت نشد"));

        Optional<Client> optionalClient = clientRepository.findByIdentifierCodeAndUnitAndService(
                dto.getIdentifierCode(), unit, service
        );

        if (optionalClient.isPresent()) {
            Client existing = optionalClient.get();
            if (!existing.isActive()) {
                existing.setActive(true);
                existing.setCreatedAt(Instant.now());
                return clientRepository.save(existing);
            } else {
                throw new RuntimeException("مشتری با این اطلاعات وجود دارد");
            }
        }

        Client client = Client.builder()
                .id(UUID.randomUUID())
                .identifierCode(dto.getIdentifierCode())
                .unit(unit)
                .service(service)
                .createdAt(Instant.now())
                .active(true)
                .build();

        return clientRepository.save(client);
    }

    @Transactional
    public Client update(UUID id, ClientDto dto) {
        Client existing = getById(id);

        Unit unit = unitRepository.findById(dto.getUnitId())
                .orElseThrow(() -> new RuntimeException("واحد یافت نشد"));

        ServiceEntity service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("سرویس یافت نشد"));

        existing.setIdentifierCode(dto.getIdentifierCode());
        existing.setUnit(unit);
        existing.setService(service);

        return clientRepository.save(existing);
    }

    @Transactional
    public void delete(UUID id) {
        Client client = getById(id);
        client.setActive(false);
        clientRepository.save(client);
    }
}
