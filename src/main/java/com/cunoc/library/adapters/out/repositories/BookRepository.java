package com.cunoc.library.adapters.out.repositories;

import com.cunoc.library.adapters.out.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, String> {

    @Query(
            value = "SELECT * FROM book b WHERE b.isbn_code = :isbnCode",
            nativeQuery = true)
    Optional<BookEntity> findBookByIsbnCode(@Param("isbnCode") String isbnCode);

    @Query(
            value = "SELECT * FROM book b WHERE b.status = :status",
            nativeQuery = true)
    List<BookEntity> getBooksByStatus(@Param("status") String status);
}
