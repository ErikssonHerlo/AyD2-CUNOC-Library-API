package com.cunoc.library.domain.models.enums;

public enum Role {
    librarian,
    student;


    public static boolean contains(String value) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
