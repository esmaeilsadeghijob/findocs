package com.husha.findocs.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String nationalId;

    private String address;

    private String phone;

    private Instant createdAt;

    private String createdBy;
}

