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
public class Period {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String fiscalYear; // مثلاً 1402

    private Instant createdAt;
}
