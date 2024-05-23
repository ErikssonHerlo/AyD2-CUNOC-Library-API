package com.cunoc.library.domain.models.enums;

public enum ReservationStatus {
    active,
    expired,
    canceled;

    public static boolean contains(String value) {
        for (ReservationStatus status : ReservationStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
