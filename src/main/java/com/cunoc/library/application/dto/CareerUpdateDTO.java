package com.cunoc.library.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public record CareerUpdateDTO(
        @NotBlank(message = "El nombre de la carrera es obligatorio")
        @NotNull(message = "El nombre de la carrera es obligatorio")
        @Size(max = 100, message = "El nombre de la carrera debe tener un máximo de 100 caracteres")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre de la carrera solo puede contener letras y espacios")
        String name
) {
}
