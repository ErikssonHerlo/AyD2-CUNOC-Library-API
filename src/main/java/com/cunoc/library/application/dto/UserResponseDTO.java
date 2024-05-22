package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record UserResponseDTO (
        String username,
        String full_name,

        String career_code, // Career code as a string since it's optional

        Role role,

        String dob,

        Date createdAt,
        Date updatedAt
){
}