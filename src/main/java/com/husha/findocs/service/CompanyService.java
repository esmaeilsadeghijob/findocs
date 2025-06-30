package com.husha.findocs.service;


import com.husha.findocs.dto.CompanyDto;
import com.husha.findocs.model.Company;
import com.husha.findocs.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repo;

    public CompanyDto create(CompanyDto dto, String username) {
        Company c = new Company();
        c.setId(null);
        c.setName(dto.getName());
        c.setNationalId(dto.getNationalId());
        c.setAddress(dto.getAddress());
        c.setPhone(dto.getPhone());
        c.setCreatedAt(Instant.now());
        c.setCreatedBy(username);

        return CompanyDto.from(repo.save(c));
    }

    public List<CompanyDto> getAll() {
        return repo.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(CompanyDto::from)
                .toList();
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }

    public CompanyDto update(UUID id, CompanyDto dto, String username) {
        Company company = repo.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));

        company.setName(dto.getName());
        company.setNationalId(dto.getNationalId());
        company.setAddress(dto.getAddress());
        company.setPhone(dto.getPhone());
        // createdAt و createdBy بدون تغییر باقی می‌مونن

        return CompanyDto.from(repo.save(company));
    }

}
