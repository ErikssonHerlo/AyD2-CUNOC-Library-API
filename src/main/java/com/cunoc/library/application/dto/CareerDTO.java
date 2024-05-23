package com.cunoc.library.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public record CareerDTO(
        @NotBlank(message = "El código de la carrera es obligatorio")
        @NotNull(message = "El código de la carrera es obligatorio")
        @Size(max = 10, message = "El código de la carrera debe tener un máximo de 10 caracteres")
        String code,

        @NotBlank(message = "El nombre de la carrera es obligatorio")
        @NotNull(message = "El nombre de la carrera es obligatorio")
        @Size(max = 100, message = "El nombre de la carrera debe tener un máximo de 100 caracteres")
        String name
) {}