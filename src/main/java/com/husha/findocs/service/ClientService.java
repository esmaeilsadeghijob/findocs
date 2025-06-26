package com.husha.findocs.service;

import com.husha.findocs.model.Client;
import com.husha.findocs.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

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
        return clientRepository.save(client);
    }

    @Transactional
    public Client update(UUID id, Client updated) {
        Client existing = getById(id);
        existing.setName(updated.getName());
        return clientRepository.save(existing);
    }

    @Transactional
    public void delete(UUID id) {
        clientRepository.deleteById(id);
    }
}
