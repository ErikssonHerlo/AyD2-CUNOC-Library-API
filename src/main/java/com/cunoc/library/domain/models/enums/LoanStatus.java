package com.cunoc.library.domain.models.enums;

public enum LoanStatus {
    lent,
    returned,
    in_default;

    public static boolean contains(String value) {
        for (LoanStatus status : LoanStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

}
