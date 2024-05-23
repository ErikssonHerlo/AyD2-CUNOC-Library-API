package com.cunoc.library.application.dto;

import java.util.Date;

public record LoanResponseDTO(
        Long id,
        String isbn_code,
        String username,
        Date loan_date,
        Date return_date,
        String status,
        Date created_at,
        Date updated_at
) {
}
