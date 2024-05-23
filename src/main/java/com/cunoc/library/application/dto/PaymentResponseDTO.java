package com.cunoc.library.application.dto;

import java.util.Date;

public record PaymentResponseDTO(
        Long id,
        Long loan_id,
        Double payment_amount,
        Date payment_date,
        Double default_amount,
        Date register_date,
        Double total_amount,
        String status
) {
}
