package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.LoanStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoanUpdateDTOTest {

        @Test
        void testConstructorAndGetters() {
                LoanUpdateDTO dto = new LoanUpdateDTO(
                        "in_default"
                );

                assertEquals("in_default", dto.status());
        }
}
