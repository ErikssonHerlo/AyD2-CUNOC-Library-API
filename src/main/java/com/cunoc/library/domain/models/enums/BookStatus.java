package com.cunoc.library.domain.models.enums;

public enum BookStatus {
    available,
    not_available;

    public static boolean contains(String value) {
        for (BookStatus status : BookStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
