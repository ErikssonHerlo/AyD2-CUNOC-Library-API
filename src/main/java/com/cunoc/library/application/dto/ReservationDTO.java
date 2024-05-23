package com.cunoc.library.application.dto;

import jakarta.validation.constraints.*;

import java.util.Date;

public record ReservationDTO(
        @NotBlank(message = "El carne del estudiante es obligatorio")
        @NotNull(message = "El carne del estudiante es obligatorio")
        String username,

        @NotBlank(message = "El código ISBN es obligatorio")
        @NotNull(message = "El código ISBN es obligatorio")
        String isbn_code,

        @NotNull(message = "La fecha de reservación es obligatoria")
        Date reservation_date
) {}
