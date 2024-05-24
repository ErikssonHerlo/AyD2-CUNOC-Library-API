package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.ReservationStatus;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationResponseDTOTest {

    @Test
    void testConstructorAndGetters() {
        Date createdAt = new Date();
        Date updatedAt = new Date();

        ReservationResponseDTO dto = new ReservationResponseDTO(
                1L,
                "user123",
                "1234567890",
                new Date(),
                ReservationStatus.active,
                createdAt,
                updatedAt
        );

        assertEquals(1L, dto.id());
        assertEquals("user123", dto.username());
        assertEquals("1234567890", dto.isbn_code());
        assertNotNull(dto.reservation_date());
        assertEquals(ReservationStatus.active, dto.status());
        assertEquals(createdAt, dto.created_at());
        assertEquals(updatedAt, dto.updated_at());
    }
}
