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
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de reservación debe estar en el formato YYYY-MM-DD")
        Date reservation_date
) {}
