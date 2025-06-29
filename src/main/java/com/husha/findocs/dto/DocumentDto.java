package com.husha.findocs.dto;

import com.husha.findocs.model.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    private UUID id;
    private UUID clientId;
    private UUID unitId;
    private UUID serviceId;
    private UUID periodId;
    private String documentNumber;
    private LocalDate documentDate;
    private String description;
    private String nature;
    private String status;

    // ✅ فیلدهای نمایشی که frontend داره استفاده می‌کنه
    private String unitName;
    private String serviceName;
    private String periodFiscalYear;

    public static DocumentDto from(Document doc) {
        return new DocumentDto(
                doc.getId(),
                doc.getClient() != null ? doc.getClient().getId() : null,
                doc.getUnit() != null ? doc.getUnit().getId() : null,
                doc.getService() != null ? doc.getService().getId() : null,
                doc.getPeriod() != null ? doc.getPeriod().getId() : null,
                doc.getDocumentNumber(),
                doc.getDocumentDate(),
                doc.getDescription(),
                doc.getNature(),
                doc.getStatus().name(),
                doc.getUnit() != null ? doc.getUnit().getName() : null,
                doc.getService() != null ? doc.getService().getName() : null,
                doc.getPeriod() != null ? doc.getPeriod().getFiscalYear() : null
        );
    }
}
