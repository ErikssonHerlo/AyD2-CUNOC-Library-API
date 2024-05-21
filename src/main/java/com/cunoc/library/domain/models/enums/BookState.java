package com.cunoc.library.domain.models.enums;

public enum BookState {
    available,
    not_available;

    public static boolean contains(String value) {
        for (BookState state : BookState.values()) {
            if (state.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
