package com.husha.findocs.repository;

import com.husha.findocs.model.Client;
import com.husha.findocs.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Project> findByClient(Client client);
}
