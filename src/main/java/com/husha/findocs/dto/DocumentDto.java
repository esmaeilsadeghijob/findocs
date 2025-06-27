package com.husha.findocs.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class DocumentDto {
    private UUID clientId;
    private UUID unitId;
    private UUID serviceId;
    private UUID periodId;

    private String documentNumber;
    private LocalDate documentDate;
    private String description;
    private String nature;
}
