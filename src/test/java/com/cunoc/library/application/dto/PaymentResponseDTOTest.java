package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.PaymentStatus;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentResponseDTOTest {

    @Test
    void testConstructorAndGetters() {
        Date paymentDate = new Date();
        Date registerDate = new Date();

        PaymentResponseDTO dto = new PaymentResponseDTO(
                1L,
                1L,
                150.00,
                paymentDate,
                50.00,
                registerDate,
                200.00,
                "paid"
        );

        assertEquals(1L, dto.id());
        assertEquals(1L, dto.loan_id());
        assertEquals(150.00, dto.payment_amount());
        assertEquals(paymentDate, dto.payment_date());
        assertEquals(50.00, dto.default_amount());
        assertEquals(registerDate, dto.register_date());
        assertEquals(200.00, dto.total_amount());
    }
}
