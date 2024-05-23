package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.ReservationStatus;
import jakarta.validation.constraints.*;

import java.util.Date;

public record ReservationUpdateDTO(
        @NotNull(message = "El estado es obligatorio")
        ReservationStatus status
) {}
