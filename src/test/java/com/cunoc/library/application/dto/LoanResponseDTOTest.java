package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.LoanStatus;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LoanResponseDTOTest {

    @Test
    void testConstructorAndGetters() {
        Date loanDate = new Date();
        Date returnDate = new Date();
        Date createdAt = new Date();
        Date updatedAt = new Date();

        LoanResponseDTO dto = new LoanResponseDTO(
                1L,
                "1234567890",
                "user123",
                loanDate,
                returnDate,
                "lent",
                createdAt,
                updatedAt
        );

        assertEquals(1L, dto.id());
        assertEquals("1234567890", dto.isbn_code());
        assertEquals("user123", dto.username());
        assertEquals(loanDate, dto.loan_date());
        assertEquals(returnDate, dto.return_date());

        assertEquals(createdAt, dto.created_at());
        assertEquals(updatedAt, dto.updated_at());
    }
}
