package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.BookStatus;

import java.util.Date;

public record BookResponseDTO(
        String isbn_code,
        String title,
        String author,
        int quantity,
        String description,
        String cover_image,
        Date publication_date,
        String editorial,
        BookStatus status,
        Date created_at,
        Date updated_at
) {}
