package com.husha.findocs.dto;

import com.husha.findocs.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ClientViewDto {
    private UUID id;
    private String identifierCode;
    private String unitName;
    private String serviceName;

    public static ClientViewDto from(Client client) {
        return new ClientViewDto(
                client.getId(),
                client.getIdentifierCode(),
                client.getUnit().getName(),
                client.getService().getName()
        );
    }
}
