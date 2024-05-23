package com.cunoc.library.domain.models.enums;

public enum PaymentStatus {
    pending,
    paid;

    public static boolean contains(String value) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
