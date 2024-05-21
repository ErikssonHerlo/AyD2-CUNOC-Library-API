package com.cunoc.library.domain.models.enums;

public enum ReservationState {
    active,
    expired,
    canceled;

    public static boolean contains(String value) {
        for (ReservationState state : ReservationState.values()) {
            if (state.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
