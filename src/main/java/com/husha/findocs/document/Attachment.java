package com.husha.findocs.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "attachments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {

    @Id
    private String id = UUID.randomUUID().toString();

    private UUID documentId;

    private String createdBy; // 👈 نام ایجادکننده مجموعه ضمائم (اختیاری)

    private List<FileMeta> attachments = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileMeta {
        private String id = UUID.randomUUID().toString();
        private String fileName;
        private String uploadedBy;
        private Instant uploadedAt;
        private String mimeType;
        private String extension;
        private String description;
        private String nature;
        private byte[] fileData;
    }
}
