package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.PaymentStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentUpdateDTOTest {

        @Test
        void testConstructorAndGetters() {
                PaymentUpdateDTO dto = new PaymentUpdateDTO(
                        "paid"
                );

                assertEquals("paid", dto.status());
        }
}
