package com.cunoc.library.application.dto;

import jakarta.validation.constraints.NotNull;

public record PaymentUpdateDTO(
        @NotNull(message = "El estado del pago es obligatorio")
        String status
) {
}
