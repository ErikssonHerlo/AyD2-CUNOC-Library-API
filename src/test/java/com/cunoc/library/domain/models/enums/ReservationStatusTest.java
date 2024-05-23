package com.cunoc.library.domain.models.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReservationStatusTest {

    @Test
    void testValues() {
        ReservationStatus[] expectedValues = {ReservationStatus.active, ReservationStatus.expired, ReservationStatus.canceled};
        ReservationStatus[] actualValues = ReservationStatus.values();
        assertArrayEquals(expectedValues, actualValues, "The values() method should return all enum constants.");
    }

    @Test
    void testContainsValidValue() {
        assertTrue(ReservationStatus.contains("active"), "The contains method should return true for 'active'.");
        assertTrue(ReservationStatus.contains("expired"), "The contains method should return true for 'expired'.");
        assertTrue(ReservationStatus.contains("canceled"), "The contains method should return true for 'canceled'.");
    }

    @Test
    void testContainsInvalidValue() {
        assertFalse(ReservationStatus.contains("unknown"), "The contains method should return false for 'unknown'.");
    }

    @Test
    void testContainsCaseInsensitive() {
        assertTrue(ReservationStatus.contains("ACTIVE"), "The contains method should return true for 'ACTIVE' ignoring case.");
        assertTrue(ReservationStatus.contains("EXPIRED"), "The contains method should return true for 'EXPIRED' ignoring case.");
        assertTrue(ReservationStatus.contains("CANCELED"), "The contains method should return true for 'CANCELED' ignoring case.");
    }
}
