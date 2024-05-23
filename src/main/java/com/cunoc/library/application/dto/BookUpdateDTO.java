package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.BookStatus;
import jakarta.validation.constraints.*;

import java.util.Date;

public record BookUpdateDTO(

        @NotNull(message = "El título es obligatorio")
        @NotBlank(message = "El título es obligatorio")
        @Size(min = 3, max = 255, message = "El título debe tener entre 3 y 255 caracteres")
        String title,

        @NotNull(message = "El autor es obligatorio")
        @NotBlank(message = "El autor es obligatorio")
        @Size(min = 3, max = 255, message = "El autor debe tener entre 3 y 255 caracteres")
        String author,

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        int quantity,

        String description,

        String cover_image,

        Date publication_date,

        String editorial,

        @NotNull(message = "El estado es obligatorio")
        @NotBlank(message = "El estado es obligatorio")
        BookStatus status
) {}
