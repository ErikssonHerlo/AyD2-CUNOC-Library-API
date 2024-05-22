package com.cunoc.library.domain.models;

import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.domain.models.enums.Role;

import java.util.Collection;
import java.util.Date;

public record User (
        String username,
        String fullName,
        Carreer carreer,
        Role role,
        String dob,
        String password,
        Date createdAt,
        Date updatedAt,
        Collection authorities
) {
    public User(UserEntity userEntity) {
        this(
                userEntity.getUsername(),
                userEntity.getFullName(),
                userEntity.getCareer() != null ? new Carreer(userEntity.getCareer()) : null,
                userEntity.getRole(),
                userEntity.getDob(),
                userEntity.getPassword(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt(),
                userEntity.getAuthorities()
        );
    }

    // No need to override methods as record provides them by default
}
