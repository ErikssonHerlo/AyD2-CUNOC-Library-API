package com.cunoc.library.domain.models.enums;

public enum NotificationStatus {
    read,
    unread;

    public static boolean contains(String value) {
        for (NotificationStatus status : NotificationStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
