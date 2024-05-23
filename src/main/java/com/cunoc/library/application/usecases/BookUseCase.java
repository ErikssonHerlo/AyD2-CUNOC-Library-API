package com.cunoc.library.application.usecases;

import com.cunoc.library.application.dao.BookDAO;
import com.cunoc.library.application.dto.BookDTO;
import com.cunoc.library.application.dto.BookResponseDTO;
import com.cunoc.library.application.dto.BookUpdateDTO;
import com.cunoc.library.infraestructure.exceptions.BadRequestException;
import com.cunoc.library.infraestructure.exceptions.ResourceAlreadyExistsException;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookUseCase {
    private final BookDAO bookDAO;

    public Optional<BookResponseDTO> getBookByIsbnCode(String isbnCode) {
        try {
            var book = bookDAO.findByIsbnCode(isbnCode);
            if (!book.isPresent()) throw new ResourceNotFoundException("book", "isbnCode", isbnCode);
            return book;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<BookResponseDTO> getAllBooks() {
        try {
            return bookDAO.findAll();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Page<BookResponseDTO> getAllBooks(Pageable pageable) {
        try {
            return bookDAO.findAll(pageable);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<BookResponseDTO> getBooksByStatus(String status) {
        try {
            return bookDAO.getBooksByStatus(status);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public BookResponseDTO saveBook(BookDTO bookDTO) {
        try {
            var isPresent = bookDAO.findByIsbnCode(bookDTO.isbn_code()).isPresent();
            if (isPresent) throw new ResourceAlreadyExistsException("book", "isbnCode", bookDTO.isbn_code());
            return bookDAO.save(bookDTO);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public BookResponseDTO updateBook(String isbnCode, BookUpdateDTO bookUpdateDTO) throws ResourceNotFoundException {
        try {
            var existingBook = bookDAO.find(isbnCode);
            if (!existingBook.isPresent()) throw new ResourceNotFoundException("book", "isbnCode", isbnCode);
            return bookDAO.update(isbnCode, bookUpdateDTO);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public void deleteBook(String isbnCode) throws ResourceNotFoundException {
        try {
            var existingBook = bookDAO.find(isbnCode);
            if (!existingBook.isPresent()) throw new ResourceNotFoundException("book", "isbnCode", isbnCode);
            bookDAO.deleteById(isbnCode);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
