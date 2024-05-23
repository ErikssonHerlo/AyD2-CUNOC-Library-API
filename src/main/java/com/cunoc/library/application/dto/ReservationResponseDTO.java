package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.ReservationStatus;

import java.util.Date;

public record ReservationResponseDTO(
        Long id,
        String username,
        String isbn_code,
        Date reservation_date,
        ReservationStatus status,
        Date created_at,
        Date updated_at
) {}
