package com.cunoc.library.domain.models.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReservationStateTest {

    @Test
    void testValues() {
        ReservationState[] expectedValues = {ReservationState.active, ReservationState.expired, ReservationState.canceled};
        ReservationState[] actualValues = ReservationState.values();
        assertArrayEquals(expectedValues, actualValues, "The values() method should return all enum constants.");
    }

    @Test
    void testContainsValidValue() {
        assertTrue(ReservationState.contains("active"), "The contains method should return true for 'active'.");
        assertTrue(ReservationState.contains("expired"), "The contains method should return true for 'expired'.");
        assertTrue(ReservationState.contains("canceled"), "The contains method should return true for 'canceled'.");
    }

    @Test
    void testContainsInvalidValue() {
        assertFalse(ReservationState.contains("unknown"), "The contains method should return false for 'unknown'.");
    }

    @Test
    void testContainsCaseInsensitive() {
        assertTrue(ReservationState.contains("ACTIVE"), "The contains method should return true for 'ACTIVE' ignoring case.");
        assertTrue(ReservationState.contains("EXPIRED"), "The contains method should return true for 'EXPIRED' ignoring case.");
        assertTrue(ReservationState.contains("CANCELED"), "The contains method should return true for 'CANCELED' ignoring case.");
    }
}
