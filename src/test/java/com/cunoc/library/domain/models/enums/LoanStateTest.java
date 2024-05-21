package com.cunoc.library.domain.models.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoanStateTest {

    @Test
    void testValues() {
        LoanState[] expectedValues = {LoanState.lent, LoanState.returned, LoanState.in_default};
        LoanState[] actualValues = LoanState.values();
        assertArrayEquals(expectedValues, actualValues, "The values() method should return all enum constants.");
    }

    @Test
    void testContainsValidValue() {
        assertTrue(LoanState.contains("lent"), "The contains method should return true for 'lent'.");
        assertTrue(LoanState.contains("returned"), "The contains method should return true for 'returned'.");
        assertTrue(LoanState.contains("in_default"), "The contains method should return true for 'in_default'.");
    }

    @Test
    void testContainsInvalidValue() {
        assertFalse(LoanState.contains("unknown"), "The contains method should return false for 'unknown'.");
    }

    @Test
    void testContainsCaseInsensitive() {
        assertTrue(LoanState.contains("LENT"), "The contains method should return true for 'LENT' ignoring case.");
        assertTrue(LoanState.contains("RETURNED"), "The contains method should return true for 'RETURNED' ignoring case.");
        assertTrue(LoanState.contains("IN_DEFAULT"), "The contains method should return true for 'IN_DEFAULT' ignoring case.");
    }
}
