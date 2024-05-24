package com.cunoc.library.application.dto;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CareerResponseDTOTest {

    @Test
    void testConstructorAndGetters() {
        Date createdAt = new Date();
        Date updatedAt = new Date();

        CareerResponseDTO dto = new CareerResponseDTO(
                "CS",
                "Computer Science",
                createdAt,
                updatedAt
        );

        assertEquals("CS", dto.code());
        assertEquals("Computer Science", dto.name());
        assertEquals(createdAt, dto.createdAt());
        assertEquals(updatedAt, dto.updatedAt());
    }
}
