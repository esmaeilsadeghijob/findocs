package com.husha.findocs.dto;

import com.husha.findocs.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private UUID id;
    private String name;
    private String nationalId;
    private String address;
    private String phone;
    private Instant createdAt;

    public static CompanyDto from(Company c) {
        return new CompanyDto(
                c.getId(),
                c.getName(),
                c.getNationalId(),
                c.getAddress(),
                c.getPhone(),
                c.getCreatedAt()
        );
    }
}
