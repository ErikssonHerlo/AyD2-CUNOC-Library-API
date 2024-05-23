package com.cunoc.library.domain.models.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoanStatusTest {

    @Test
    void testValues() {
        LoanStatus[] expectedValues = {LoanStatus.lent, LoanStatus.returned, LoanStatus.in_default};
        LoanStatus[] actualValues = LoanStatus.values();
        assertArrayEquals(expectedValues, actualValues, "The values() method should return all enum constants.");
    }

    @Test
    void testContainsValidValue() {
        assertTrue(LoanStatus.contains("lent"), "The contains method should return true for 'lent'.");
        assertTrue(LoanStatus.contains("returned"), "The contains method should return true for 'returned'.");
        assertTrue(LoanStatus.contains("in_default"), "The contains method should return true for 'in_default'.");
    }

    @Test
    void testContainsInvalidValue() {
        assertFalse(LoanStatus.contains("unknown"), "The contains method should return false for 'unknown'.");
    }

    @Test
    void testContainsCaseInsensitive() {
        assertTrue(LoanStatus.contains("LENT"), "The contains method should return true for 'LENT' ignoring case.");
        assertTrue(LoanStatus.contains("RETURNED"), "The contains method should return true for 'RETURNED' ignoring case.");
        assertTrue(LoanStatus.contains("IN_DEFAULT"), "The contains method should return true for 'IN_DEFAULT' ignoring case.");
    }
}
