package com.husha.findocs.repository;

import com.husha.findocs.model.CustomerIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerIdentifierRepository extends JpaRepository<CustomerIdentifier, UUID> {
    Optional<CustomerIdentifier> findByCode(String code);

    boolean existsByCode(String code);
}
