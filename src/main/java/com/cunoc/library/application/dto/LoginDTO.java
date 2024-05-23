package com.cunoc.library.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginDTO(
        @NotNull(message = "El username es obligatorio")
        @NotBlank(message = "El username es obligatorio")
        String username,
        @NotNull(message = "La password es obligatoria")
        @NotBlank(message = "La password es obligatoria")
        String password
)
{

}
