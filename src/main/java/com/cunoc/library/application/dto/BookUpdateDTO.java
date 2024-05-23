package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.BookStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record BookUpdateDTO(

        @NotNull(message = "El título es obligatorio")
        @Size(min = 3, max = 255, message = "El título debe tener entre 3 y 255 caracteres")
        String title,

        @NotNull(message = "El autor es obligatorio")
        @Size(min = 3, max = 255, message = "El autor debe tener entre 3 y 255 caracteres")
        String author,

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
