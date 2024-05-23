package com.cunoc.library.application.dao;

import com.cunoc.library.adapters.out.entities.BookEntity;
import com.cunoc.library.application.dto.BookDTO;
import com.cunoc.library.application.dto.BookResponseDTO;
import com.cunoc.library.application.dto.BookUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    Optional<BookEntity> find(String isbnCode);
    Optional<BookResponseDTO> findByIsbnCode(String isbnCode);
    List<BookResponseDTO> findAll();
    Page<BookResponseDTO> findAll(Pageable pageable);
    BookResponseDTO save(BookDTO book);
    BookResponseDTO update(String isbnCode, BookUpdateDTO book);
    void deleteById(String isbnCode);
    List<BookResponseDTO> getBooksByStatus(String status);
}
