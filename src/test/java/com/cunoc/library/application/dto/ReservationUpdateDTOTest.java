package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.ReservationStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationUpdateDTOTest {

        @Test
        void testConstructorAndGetters() {
                ReservationUpdateDTO dto = new ReservationUpdateDTO(
                        ReservationStatus.expired
                );

                assertEquals(ReservationStatus.expired, dto.status());
        }
}
