package com.cunoc.library.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDTOTest {

        @Test
        void testConstructorAndGetters() {
                LoginDTO dto = new LoginDTO(
                        "user123",
                        "password123"
                );

                assertEquals("user123", dto.username());
                assertEquals("password123", dto.password());
        }
}
