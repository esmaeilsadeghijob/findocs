package com.husha.findocs.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ClientDto {
    private String identifierCode; // 👈 شناسه مشتری (مثلاً کد ملی یا شماره اشتراک)
    private UUID unitId;
    private UUID serviceId;
}


