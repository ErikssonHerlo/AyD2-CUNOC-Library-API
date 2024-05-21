package com.cunoc.library.domain.models.enums;

public enum PaymentState {
    pending,
    paid;

    public static boolean contains(String value) {
        for (PaymentState state : PaymentState.values()) {
            if (state.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
