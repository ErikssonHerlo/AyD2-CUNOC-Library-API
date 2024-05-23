package com.cunoc.library.domain.models;

import com.cunoc.library.adapters.out.entities.BookEntity;
import com.cunoc.library.domain.models.enums.BookStatus;

import java.util.Date;

public record Book(
        String ISBNCode,
        String title,
        String author,
        int quantity,
        String description,
        String coverImage,
        Date publicationDate,
        String editorial,
        BookStatus status,
        Date createdAt,
        Date updatedAt
) {
    public Book(BookEntity bookEntity) {
        this(
                bookEntity.getISBNCode(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getQuantity(),
                bookEntity.getDescription(),
                bookEntity.getCoverImage(),
                bookEntity.getPublicationDate(),
                bookEntity.getEditorial(),
                bookEntity.getStatus(),
                bookEntity.getCreatedAt(),
                bookEntity.getUpdatedAt()
        );
    }
}
