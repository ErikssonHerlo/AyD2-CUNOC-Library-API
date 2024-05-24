package com.cunoc.library.application.dto;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationDTOTest {

        @Test
        void testConstructorAndGetters() {
                Date reservationDate = new Date();

                ReservationDTO dto = new ReservationDTO(
                        "user123",
                        "1234567890",
                        reservationDate
                );

                assertEquals("user123", dto.username());
                assertEquals("1234567890", dto.isbn_code());
                assertEquals(reservationDate, dto.reservation_date());
        }
}
