package com.cunoc.library.domain.models.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookStatusTest {

    @Test
    void testValues() {
        BookStatus[] expectedValues = {BookStatus.available, BookStatus.not_available};
        BookStatus[] actualValues = BookStatus.values();
        assertArrayEquals(expectedValues, actualValues, "The values() method should return all enum constants.");
    }

    @Test
    void testContainsValidValue() {
        assertTrue(BookStatus.contains("available"), "The contains method should return true for 'available'.");
        assertTrue(BookStatus.contains("not_available"), "The contains method should return true for 'not_available'.");
    }

    @Test
    void testContainsInvalidValue() {
        assertFalse(BookStatus.contains("unknown"), "The contains method should return false for 'unknown'.");
    }

    @Test
    void testContainsCaseInsensitive() {
        assertTrue(BookStatus.contains("AVAILABLE"), "The contains method should return true for 'AVAILABLE' ignoring case.");
        assertTrue(BookStatus.contains("NOT_AVAILABLE"), "The contains method should return true for 'NOT_AVAILABLE' ignoring case.");
    }
}
