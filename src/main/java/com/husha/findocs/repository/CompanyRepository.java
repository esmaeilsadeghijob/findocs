package com.husha.findocs.repository;

import com.husha.findocs.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    List<Company> findAllByOrderByCreatedAtDesc();
}

