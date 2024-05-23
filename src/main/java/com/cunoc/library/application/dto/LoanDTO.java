package com.cunoc.library.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record LoanDTO(
        @NotNull(message = "El código del libro es obligatorio")
        @NotBlank(message = "El código del libro no puede estar vacío")
        String isbn_code,

        @NotBlank(message = "El carne del Estudiante no puede estar vacío")
        @NotNull(message = "El carne del Estudiante es obligatorio")
        String username,

        @Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d{2}$", message = "La fecha del préstamo debe tener el formato MM/dd/yyyy")
        @NotNull(message = "La fecha del préstamo es obligatoria")
        Date loan_date
) {
}
