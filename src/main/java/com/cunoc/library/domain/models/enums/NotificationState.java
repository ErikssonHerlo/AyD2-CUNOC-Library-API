package com.cunoc.library.domain.models.enums;

public enum NotificationState {
    read,
    unread;

    public static boolean contains(String value) {
        for (NotificationState state : NotificationState.values()) {
            if (state.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
