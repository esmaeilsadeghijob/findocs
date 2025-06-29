package com.husha.findocs.controller;

import com.husha.findocs.dto.ClientDto;
import com.husha.findocs.dto.ClientViewDto;
import com.husha.findocs.model.Client;
import com.husha.findocs.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

    private final ClientService clientService;

    // دریافت همه مشتری‌ها با DTO برای نمایش
    @GetMapping
    public ResponseEntity<List<ClientViewDto>> getAll() {
        List<ClientViewDto> list = clientService.getAll().stream()
                .filter(Client::isActive)
                .map(ClientViewDto::from)
                .toList();
        return ResponseEntity.ok(list);
    }

    // دریافت مشتری کامل بر اساس ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(clientService.getById(id));
    }

    // ساخت مشتری با Entity (برای تست)
    @PostMapping("/raw")
    public ResponseEntity<Client> createRaw(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.create(client));
    }

    // ساخت مشتری با DTO
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientDto dto) {
        return ResponseEntity.ok(clientService.create(dto));
    }

    // ویرایش مشتری با DTO
    @PutMapping("/{id}")
    public ResponseEntity<ClientViewDto> update(@PathVariable UUID id, @RequestBody ClientDto dto) {
        Client updated = clientService.update(id, dto);
        return ResponseEntity.ok(ClientViewDto.from(updated));
    }


    // حذف مشتری
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
