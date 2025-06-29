package com.husha.findocs.controller;

import com.husha.findocs.dto.ClientDto;
import com.husha.findocs.dto.ClientViewDto;
import com.husha.findocs.model.Client;
import com.husha.findocs.model.Unit;
import com.husha.findocs.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

    private final ClientService clientService;

//    @GetMapping
//    public ResponseEntity<List<Client>> getAll() {
//        return ResponseEntity.ok(clientService.getAll());
//    }

    @GetMapping
    public ResponseEntity<List<ClientViewDto>> getAll() {
        List<ClientViewDto> list = clientService.getAll().stream()
                .map(ClientViewDto::from)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(clientService.getById(id));
    }

    @PostMapping("raw")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.create(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable UUID id, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.update(id, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientDto dto) {
        return ResponseEntity.ok(clientService.create(dto));
    }

}
