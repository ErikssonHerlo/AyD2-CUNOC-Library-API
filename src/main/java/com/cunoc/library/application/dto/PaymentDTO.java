package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.PaymentStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record PaymentDTO(
        @Min(value = 1, message = "El ID del préstamo debe ser mayor a 0")
        @NotNull(message = "El ID del préstamo es obligatorio")
        Long loan_id,

        @NotNull(message = "El monto del pago es obligatorio")
        @Min(value = 1, message = "El monto del pago debe ser mayor a 0")
        Double payment_amount,

        @NotNull(message = "La fecha del pago es obligatoria")
        @Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d{2}$", message = "La fecha del pago debe tener el formato MM/dd/yyyy")
        Date payment_date


) {
}
