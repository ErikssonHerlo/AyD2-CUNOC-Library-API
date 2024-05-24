package com.cunoc.library.domain.models.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationStatusTest {

    @Test
    void testValues() {
        NotificationStatus[] expectedValues = {NotificationStatus.read, NotificationStatus.unread};
        NotificationStatus[] actualValues = NotificationStatus.values();
        assertArrayEquals(expectedValues, actualValues, "The values() method should return all enum constants.");
    }

    @Test
    void testContainsValidValue() {
        assertTrue(NotificationStatus.contains("read"), "The contains method should return true for 'read'.");
        assertTrue(NotificationStatus.contains("unread"), "The contains method should return true for 'unread'.");
    }

    @Test
    void testContainsInvalidValue() {
        assertFalse(NotificationStatus.contains("unknown"), "The contains method should return false for 'unknown'.");
    }

    @Test
    void testContainsCaseInsensitive() {
        assertTrue(NotificationStatus.contains("READ"), "The contains method should return true for 'READ' ignoring case.");
        assertTrue(NotificationStatus.contains("UNREAD"), "The contains method should return true for 'UNREAD' ignoring case.");
    }
}
