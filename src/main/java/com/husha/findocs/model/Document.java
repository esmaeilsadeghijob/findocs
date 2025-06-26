package com.husha.findocs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    private UUID id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Project project;

    @ManyToOne
    private User user;

    private String documentNumber;
    private String fiscalYear;
    private LocalDate documentDate;
    private String description;
    private String nature;
    private Instant createdAt;
}
