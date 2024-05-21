package com.cunoc.library.application.dto;

import java.util.Date;

public record CareerResponseDTO(
        String code,
        String name,
        Date createdAt,
        Date updatedAt
) {
}
