package com.cunoc.library.adapters.out.entities;

import com.cunoc.library.domain.models.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "book")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    @Column(name = "ISBN_code", nullable = false, length = 11)
    private String ISBNCode;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "author", nullable = false, length = 255)
    private String author;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "cover_image", columnDefinition = "TEXT")
    private String coverImage;

    @Column(name = "publication_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationDate;

    @Column(name = "editorial", length = 255)
    private String editorial;

    @Column(name = "status", columnDefinition = "ENUM('available', 'not_available')")
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Column(name = "created_at", nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date updatedAt;

}
