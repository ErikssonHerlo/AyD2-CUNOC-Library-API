package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserUpdateDTOTest {

        @Test
        void testConstructorAndGetters() {
                UserUpdateDTO dto = new UserUpdateDTO(
                        "John Doe",
                        "CS",
                        Role.student,
                        "2000-01-01",
                        "newpassword123"
                );

                assertEquals("John Doe", dto.full_name());
                assertEquals("CS", dto.career_code());
                assertEquals(Role.student, dto.role());
                assertEquals("2000-01-01", dto.dob());
                assertEquals("newpassword123", dto.password());
        }
}
