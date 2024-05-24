package com.cunoc.library.application.dto;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LoanDTOTest {

        @Test
        void testConstructorAndGetters() {
                Date loanDate = new Date();

                LoanDTO dto = new LoanDTO(
                        "1234567890",
                        "user123",
                        loanDate
                );

                assertEquals("1234567890", dto.isbn_code());
                assertEquals("user123", dto.username());
                assertEquals(loanDate, dto.loan_date());
        }
}
