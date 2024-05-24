package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.PaymentStatus;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentDTOTest {

        @Test
        void testConstructorAndGetters() {
                Date paymentDate = new Date();

                PaymentDTO dto = new PaymentDTO(
                        1L,
                        150.00,
                        paymentDate
                );

                assertEquals(1L, dto.loan_id());
                assertEquals(150.00, dto.payment_amount());
                assertEquals(paymentDate, dto.payment_date());

        }
}
