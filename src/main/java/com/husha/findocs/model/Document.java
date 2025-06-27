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
    private User user;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;

    private String documentNumber;
    private LocalDate documentDate;
    private String description;
    private String nature;

    private Instant createdAt;
    private String createdBy;
}
