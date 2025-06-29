package com.husha.findocs.repository;

import com.husha.findocs.model.Client;
import com.husha.findocs.model.Unit;
import com.husha.findocs.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    boolean existsByIdentifierCodeAndUnitAndService(String identifierCode, Unit unit, ServiceEntity service);
    Optional<Client> findByIdentifierCodeAndUnitAndService(String identifierCode, Unit unit, ServiceEntity service);
}
