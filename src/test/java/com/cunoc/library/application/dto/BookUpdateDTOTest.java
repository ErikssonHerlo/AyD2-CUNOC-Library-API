package com.cunoc.library.application.dto;

import com.cunoc.library.domain.models.enums.BookStatus;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BookUpdateDTOTest {

        @Test
        void testConstructorAndGetters() {
                Date publicationDate = new Date();

                BookUpdateDTO dto = new BookUpdateDTO(
                        "Book Title",
                        "Author Name",
                        5,
                        "Book Description",
                        "http://example.com/cover.jpg",
                        publicationDate,
                        "Editorial Name",
                        BookStatus.available
                );

                assertEquals("Book Title", dto.title());
                assertEquals("Author Name", dto.author());
                assertEquals(5, dto.quantity());
                assertEquals("Book Description", dto.description());
                assertEquals("http://example.com/cover.jpg", dto.cover_image());
                assertEquals(publicationDate, dto.publication_date());
                assertEquals("Editorial Name", dto.editorial());
                assertEquals(BookStatus.available, dto.status());
        }
}
