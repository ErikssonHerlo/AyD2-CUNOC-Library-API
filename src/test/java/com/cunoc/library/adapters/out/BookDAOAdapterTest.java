package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.BookEntity;
import com.cunoc.library.adapters.out.repositories.BookRepository;
import com.cunoc.library.application.dto.BookDTO;
import com.cunoc.library.application.dto.BookResponseDTO;
import com.cunoc.library.application.dto.BookUpdateDTO;
import com.cunoc.library.domain.models.enums.BookStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BookDAOAdapterTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookDAOAdapter bookDAOAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFind() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setISBNCode("1234567890");

        when(bookRepository.findById(anyString())).thenReturn(Optional.of(bookEntity));

        Optional<BookEntity> result = bookDAOAdapter.find("1234567890");

        assertTrue(result.isPresent());
        assertEquals("1234567890", result.get().getISBNCode());
    }

    @Test
    void testFindByIsbnCode() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setISBNCode("1234567890");
        bookEntity.setTitle("Test Title");

        when(bookRepository.findBookByIsbnCode(anyString())).thenReturn(Optional.of(bookEntity));

        Optional<BookResponseDTO> result = bookDAOAdapter.findByIsbnCode("1234567890");

        assertTrue(result.isPresent());
        assertEquals("1234567890", result.get().isbn_code());
        assertEquals("Test Title", result.get().title());
    }

    @Test
    void testFindAll() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setISBNCode("1234567890");

        when(bookRepository.findAll()).thenReturn(Collections.singletonList(bookEntity));

        List<BookResponseDTO> result = bookDAOAdapter.findAll();

        assertEquals(1, result.size());
        assertEquals("1234567890", result.get(0).isbn_code());
    }

    @Test
    void testFindAllPageable() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setISBNCode("1234567890");

        Page<BookEntity> bookPage = new PageImpl<>(Collections.singletonList(bookEntity));
        when(bookRepository.findAll(any(Pageable.class))).thenReturn(bookPage);

        Page<BookResponseDTO> result = bookDAOAdapter.findAll(Pageable.unpaged());

        assertEquals(1, result.getContent().size());
        assertEquals("1234567890", result.getContent().get(0).isbn_code());
    }

    @Test
    void testSave() {
        BookDTO bookDTO = new BookDTO(
                "1234567890",
                "Test Title",
                "Test Author",
                10,
                "Test Description",
                "http://example.com/image.jpg",
                new Date(),
                "Test Editorial",
                BookStatus.available
        );

        BookEntity bookEntity = new BookEntity();
        bookEntity.setISBNCode("1234567890");

        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);

        BookResponseDTO result = bookDAOAdapter.save(bookDTO);

        assertEquals("1234567890", result.isbn_code());
    }

    @Test
    void testUpdate() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setISBNCode("1234567890");
        bookEntity.setCreatedAt(new Date());

        BookUpdateDTO bookUpdateDTO = new BookUpdateDTO(
                "Updated Title",
                "Updated Author",
                5,
                "Updated Description",
                "http://example.com/updated_image.jpg",
                new Date(),
                "Updated Editorial",
                BookStatus.not_available
        );

        when(bookRepository.findById(anyString())).thenReturn(Optional.of(bookEntity));
        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);

        BookResponseDTO result = bookDAOAdapter.update("1234567890", bookUpdateDTO);

        assertEquals("1234567890", result.isbn_code());
        assertEquals("Updated Title", result.title());
    }

    @Test
    void testDeleteById() {
        doNothing().when(bookRepository).deleteById(anyString());

        bookDAOAdapter.deleteById("1234567890");

        verify(bookRepository, times(1)).deleteById("1234567890");
    }

    @Test
    void testGetBooksByStatus() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setISBNCode("1234567890");

        when(bookRepository.getBooksByStatus(anyString())).thenReturn(Collections.singletonList(bookEntity));

        List<BookResponseDTO> result = bookDAOAdapter.getBooksByStatus("available");

        assertEquals(1, result.size());
        assertEquals("1234567890", result.get(0).isbn_code());
    }
}
