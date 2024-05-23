package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.BookEntity;
import com.cunoc.library.adapters.out.repositories.BookRepository;
import com.cunoc.library.application.dao.BookDAO;
import com.cunoc.library.application.dto.BookDTO;
import com.cunoc.library.application.dto.BookResponseDTO;
import com.cunoc.library.application.dto.BookUpdateDTO;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookDAOAdapter implements BookDAO {

    private final BookRepository bookRepository;

    @Override
    public Optional<BookEntity> find(String isbnCode) {
        return bookRepository.findById(isbnCode);
    }

    @Override
    public Optional<BookResponseDTO> findByIsbnCode(String isbnCode) {
        return bookRepository.findBookByIsbnCode(isbnCode).map(this::mapToResponseDTO);
    }

    @Override
    public List<BookResponseDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookResponseDTO> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(this::mapToResponseDTO);
    }

    @Override
    public BookResponseDTO save(BookDTO bookDTO) {
        BookEntity bookEntity = mapToEntity(bookDTO);
        bookRepository.save(bookEntity);
        return mapToResponseDTO(bookEntity);
    }

    @Override
    public BookResponseDTO update(String isbnCode, BookUpdateDTO bookUpdateDTO) {
        BookEntity bookEntity = bookRepository.findById(isbnCode)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "isbnCode", isbnCode));

        bookEntity = BookEntity.builder()
                .ISBNCode(isbnCode)
                .title(bookUpdateDTO.title())
                .author(bookUpdateDTO.author())
                .quantity(bookUpdateDTO.quantity())
                .description(bookUpdateDTO.description())
                .coverImage(bookUpdateDTO.cover_image())
                .publicationDate(bookUpdateDTO.publication_date())
                .editorial(bookUpdateDTO.editorial())
                .status(bookUpdateDTO.status())
                .createdAt(bookEntity.getCreatedAt()) // Preserve the original creation date
                .updatedAt(new Date())
                .build();

        bookRepository.save(bookEntity);
        return mapToResponseDTO(bookEntity);
    }

    @Override
    public void deleteById(String isbnCode) {
        bookRepository.deleteById(isbnCode);
    }

    @Override
    public List<BookResponseDTO> getBooksByStatus(String status) {
        return bookRepository.getBooksByStatus(status)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private BookResponseDTO mapToResponseDTO(BookEntity bookEntity) {
        return new BookResponseDTO(
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

    private BookEntity mapToEntity(BookDTO bookDTO) {
        return BookEntity.builder()
                .ISBNCode(bookDTO.isbn_code())
                .title(bookDTO.title())
                .author(bookDTO.author())
                .quantity(bookDTO.quantity())
                .description(bookDTO.description())
                .coverImage(bookDTO.cover_image())
                .publicationDate(bookDTO.publication_date())
                .editorial(bookDTO.editorial())
                .status(bookDTO.status())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }
}
