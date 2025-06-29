package com.husha.findocs.repository;

import com.husha.findocs.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UnitRepository extends JpaRepository<Unit, UUID> {
    @Query("select u from Unit u where u.id = :id")
    Optional<Unit> fetchWithAllFields(@Param("id") UUID id);

}
