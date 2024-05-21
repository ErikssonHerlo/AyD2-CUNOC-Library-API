package com.cunoc.library.domain.models.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookStateTest {

    @Test
    void testValues() {
        BookState[] expectedValues = {BookState.available, BookState.not_available};
        BookState[] actualValues = BookState.values();
        assertArrayEquals(expectedValues, actualValues, "The values() method should return all enum constants.");
    }

    @Test
    void testContainsValidValue() {
        assertTrue(BookState.contains("available"), "The contains method should return true for 'available'.");
        assertTrue(BookState.contains("not_available"), "The contains method should return true for 'not_available'.");
    }

    @Test
    void testContainsInvalidValue() {
        assertFalse(BookState.contains("unknown"), "The contains method should return false for 'unknown'.");
    }

    @Test
    void testContainsCaseInsensitive() {
        assertTrue(BookState.contains("AVAILABLE"), "The contains method should return true for 'AVAILABLE' ignoring case.");
        assertTrue(BookState.contains("NOT_AVAILABLE"), "The contains method should return true for 'NOT_AVAILABLE' ignoring case.");
    }
}
