package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(

        @NotEmpty(message = "El nombre completo es obligatorio")
        @NotNull(message = "El nombre completo es obligatorio")
        @Size(min = 3, max = 255, message = "El nombre completo debe tener entre 3 y 255 caracteres")
        String full_name,

        @Size(max = 45, message = "El código de la carrera debe tener hasta 45 caracteres")
        String career_code, // Career code as a string since it's optional

        @NotEmpty(message = "El correo es obligatorio")
        @NotNull(message = "El rol es obligatorio")
        Role role,

        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de nacimiento debe estar en el formato YYYY-MM-DD")
        String dob,

        @NotEmpty(message = "La contraseña es obligatoria")
        @NotNull(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password
) {}
