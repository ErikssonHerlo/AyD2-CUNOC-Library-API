package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.Role;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserResponseDTOTest {

    @Test
    void testConstructorAndGetters() {
        Date createdAt = new Date();
        Date updatedAt = new Date();

        UserResponseDTO dto = new UserResponseDTO(
                "user123",
                "John Doe",
                "CS",
                Role.student,
                "2000-01-01",
                createdAt,
                updatedAt
        );

        assertEquals("user123", dto.username());
        assertEquals("John Doe", dto.full_name());
        assertEquals("CS", dto.career_code());
        assertEquals(Role.student, dto.role());
        assertEquals("2000-01-01", dto.dob());
        assertEquals(createdAt, dto.createdAt());
        assertEquals(updatedAt, dto.updatedAt())
        ;
    }
}
