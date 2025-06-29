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
public class CustomerIdentifier {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String code; // کد یا شناسه منحصر‌به‌فرد

    private String description; // توضیح دلخواه یا برچسب

    private Instant createdAt;
}
