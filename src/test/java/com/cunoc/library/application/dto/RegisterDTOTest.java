package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterDTOTest {

        @Test
        void testConstructorAndGetters() {
                RegisterDTO dto = new RegisterDTO(
                        "user123",
                        "John Doe",
                        "CS",
                        Role.student,
                        "2000-01-01",
                        "password123"
                );

                assertEquals("user123", dto.username());
                assertEquals("John Doe", dto.full_name());
                assertEquals("CS", dto.career_code());
                assertEquals(Role.student, dto.role());
                assertEquals("2000-01-01", dto.dob());
                assertEquals("password123", dto.password());
        }
}
