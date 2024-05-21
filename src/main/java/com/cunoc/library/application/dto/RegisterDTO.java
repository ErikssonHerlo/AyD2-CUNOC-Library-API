package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.Role;

import jakarta.validation.constraints.*;

public record RegisterDTO(
        @NotNull(message = "El nombre de usuario es obligatorio")
        @Size(min = 3, max = 255, message = "El nombre de usuario debe tener entre 3 y 255 caracteres")
        String username,

        @NotNull(message = "El nombre completo es obligatorio")
        @Size(min = 3, max = 255, message = "El nombre completo debe tener entre 3 y 255 caracteres")
        String full_name,

        @Size(max = 45, message = "El código de la carrera debe tener hasta 45 caracteres")
        String career_code, // Career code as a string since it's optional

        @NotNull(message = "El rol es obligatorio")
        Role role,

        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de nacimiento debe estar en el formato YYYY-MM-DD")
        String dob,

        @NotNull(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password
) {}
