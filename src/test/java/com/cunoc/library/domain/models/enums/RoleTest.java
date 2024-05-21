package com.cunoc.library.domain.models.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoleTest {

    @Test
    void testValues() {
        Role[] expectedValues = {Role.librarian, Role.student};
        Role[] actualValues = Role.values();
        assertArrayEquals(expectedValues, actualValues, "The values() method should return all enum constants.");
    }

    @Test
    void testContainsValidValue() {
        assertTrue(Role.contains("librarian"), "The contains method should return true for 'librarian'.");
        assertTrue(Role.contains("student"), "The contains method should return true for 'student'.");
    }

    @Test
    void testContainsInvalidValue() {
        assertFalse(Role.contains("unknown"), "The contains method should return false for 'unknown'.");
    }

    @Test
    void testContainsCaseInsensitive() {
        assertTrue(Role.contains("LIBRARIAN"), "The contains method should return true for 'LIBRARIAN' ignoring case.");
        assertTrue(Role.contains("STUDENT"), "The contains method should return true for 'STUDENT' ignoring case.");
    }
}
