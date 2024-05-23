package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.BookStatus;
import jakarta.validation.constraints.*;

import java.util.Date;

public record BookDTO(
        @NotEmpty(message = "El código ISBN es obligatorio")
        @NotNull(message = "El código ISBN es obligatorio")
        @Size(min = 10, max = 11, message = "El código ISBN debe tener entre 10 y 11 caracteres")
        String isbn_code,

        @NotEmpty(message = "El título es obligatorio")
        @NotNull(message = "El título es obligatorio")
        @Size(min = 3, max = 255, message = "El título debe tener entre 3 y 255 caracteres")
        String title,

        @NotEmpty(message = "El autor es obligatorio")
        @NotNull(message = "El autor es obligatorio")
        @Size(min = 3, max = 255, message = "El autor debe tener entre 3 y 255 caracteres")
        String author,

        @NotNull(message = "La cantidad es  obligatoria")
        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        int quantity,

        String description,

        String cover_image,

        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de nacimiento debe estar en el formato YYYY-MM-DD")
        Date publication_date,

        String editorial,

        @NotNull(message = "El estado es obligatorio")
        BookStatus status
) {}
