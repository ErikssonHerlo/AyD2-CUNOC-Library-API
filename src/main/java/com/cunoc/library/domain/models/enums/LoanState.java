package com.cunoc.library.domain.models.enums;

public enum LoanState {
    lent,
    returned,
    in_default;

    public static boolean contains(String value) {
        for (LoanState state : LoanState.values()) {
            if (state.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

}
