package com.cunoc.library.application.dto;

import jakarta.validation.constraints.NotNull;

public record LoanUpdateDTO(
        @NotNull(message = "El estado del préstamo es obligatorio")
        String status
) {
}
