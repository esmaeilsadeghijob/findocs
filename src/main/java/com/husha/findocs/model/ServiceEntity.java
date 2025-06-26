package com.husha.findocs.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "service") // چون "service" در SQL رزرو شده
public class ServiceEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name; // نام سرویس

    private Instant createdAt;
}
