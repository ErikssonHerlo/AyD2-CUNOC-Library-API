package com.cunoc.library.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CareerDTOTest {

        @Test
        void testConstructorAndGetters() {
                CareerDTO dto = new CareerDTO(
                        "CS",
                        "Computer Science"
                );

                assertEquals("CS", dto.code());
                assertEquals("Computer Science", dto.name());
        }
}
