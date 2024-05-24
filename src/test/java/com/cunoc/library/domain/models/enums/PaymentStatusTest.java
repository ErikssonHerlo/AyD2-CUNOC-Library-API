package com.cunoc.library.domain.models.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentStatusTest {

    @Test
    void testValues() {
        PaymentStatus[] expectedValues = {PaymentStatus.pending, PaymentStatus.paid};
        PaymentStatus[] actualValues = PaymentStatus.values();
        assertArrayEquals(expectedValues, actualValues, "The values() method should return all enum constants.");
    }

    @Test
    void testContainsValidValue() {
        assertTrue(PaymentStatus.contains("pending"), "The contains method should return true for 'pending'.");
        assertTrue(PaymentStatus.contains("paid"), "The contains method should return true for 'paid'.");
    }

    @Test
    void testContainsInvalidValue() {
        assertFalse(PaymentStatus.contains("unknown"), "The contains method should return false for 'unknown'.");
    }

    @Test
    void testContainsCaseInsensitive() {
        assertTrue(PaymentStatus.contains("PENDING"), "The contains method should return true for 'PENDING' ignoring case.");
        assertTrue(PaymentStatus.contains("PAID"), "The contains method should return true for 'PAID' ignoring case.");
    }
}
