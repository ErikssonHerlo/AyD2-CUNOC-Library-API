package com.cunoc.library.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CareerUpdateDTOTest {

        @Test
        void testConstructorAndGetters() {
                CareerUpdateDTO dto = new CareerUpdateDTO(
                        "Computer Science"
                );

                assertEquals("Computer Science", dto.name());
        }
}
