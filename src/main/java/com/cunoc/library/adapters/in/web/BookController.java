package com.cunoc.library.adapters.in.web;

import com.cunoc.library.application.dto.BookDTO;
import com.cunoc.library.application.dto.BookResponseDTO;
import com.cunoc.library.application.dto.BookUpdateDTO;
import com.cunoc.library.application.dto.CareerResponseDTO;
import com.cunoc.library.application.payload.ApiResponse;
import com.cunoc.library.application.payload.PaginatedResponse;
import com.cunoc.library.application.usecases.BookUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
@RequiredArgsConstructor
@EnableMethodSecurity
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {

    private final BookUseCase bookUseCase;

    @GetMapping("/{ISBNCode}")
    public ApiResponse<BookResponseDTO> getBookByIsbnCode(@PathVariable String ISBNCode) {
        return new ApiResponse(HttpStatus.OK.value(), "Success", HttpStatus.OK, bookUseCase.getBookByIsbnCode(ISBNCode));
    }

    @GetMapping("/all")
    public ApiResponse<List<BookResponseDTO>> getAllBooks() {
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", HttpStatus.OK, bookUseCase.getAllBooks());
    }

    @GetMapping()
    public PaginatedResponse<List<BookResponseDTO>> getAllBooks(@PageableDefault(page = 0,size = 10) Pageable pageable) {
        Page<BookResponseDTO> bookPage = bookUseCase.getAllBooks(pageable);
        return new PaginatedResponse<>(
                HttpStatus.OK.value(),"Success",
                HttpStatus.OK,bookPage.getContent(),
                bookPage.getPageable(),
                bookPage.isLast(),
                bookPage.isFirst(),
                bookPage.hasNext(),
                bookPage.hasPrevious(),
                bookPage.getTotalPages(),
                (int) bookPage.getTotalElements());
     }

    @PreAuthorize("hasAuthority('librarian')")
    @PostMapping
    public ApiResponse<BookResponseDTO> saveBook(@RequestBody BookDTO book) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", HttpStatus.OK, bookUseCase.saveBook(book));
    }

    @PutMapping("/{ISBNCode}")
    public ApiResponse<BookResponseDTO> updateBook(@PathVariable String ISBNCode, @RequestBody BookUpdateDTO book) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", HttpStatus.OK, bookUseCase.updateBook(ISBNCode, book));
    }

    @PreAuthorize("hasAuthority('librarian')")
    @DeleteMapping("/{ISBNCode}")
    public ResponseEntity<?> deleteBook(@PathVariable String ISBNCode) {
        bookUseCase.deleteBook(ISBNCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<BookResponseDTO>> getBooksByStatus(@PathVariable String status) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", HttpStatus.OK, bookUseCase.getBooksByStatus(status));
    }
}
