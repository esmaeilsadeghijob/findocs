package com.husha.findocs.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class DocumentDto {
    private UUID clientId;
    private UUID projectId;
    private String documentNumber;
    private String fiscalYear;
    private LocalDate documentDate;
    private String description;
    private String nature;
}

