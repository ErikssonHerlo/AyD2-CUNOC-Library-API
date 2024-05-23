package com.cunoc.library.adapters.in.web;

import com.cunoc.library.application.dto.LoanDTO;
import com.cunoc.library.application.dto.LoanResponseDTO;
import com.cunoc.library.application.payload.ApiResponse;
import com.cunoc.library.application.payload.PaginatedResponse;
import com.cunoc.library.application.usecases.LoanUseCase;
import com.cunoc.library.domain.models.enums.LoanStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/loan")
@RequiredArgsConstructor
@EnableMethodSecurity
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoanController {
    private final LoanUseCase loanUseCase;

    @PreAuthorize("hasAuthority('librarian')")
    @PostMapping()
    public ApiResponse<LoanResponseDTO> createLoan(@RequestBody @Valid LoanDTO loanDTO) {
        LoanResponseDTO newLoan = loanUseCase.createLoan(loanDTO);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Loan created successfully", HttpStatus.CREATED, newLoan);
    }

    @PutMapping("/{loanId}/return")
    public ApiResponse<LoanResponseDTO> returnLoan(
            @PathVariable Long loanId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid Date returnDate) {
        LoanResponseDTO returnedLoan = loanUseCase.returnLoan(loanId, returnDate);
        return new ApiResponse<>(HttpStatus.OK.value(), "Loan returned successfully", HttpStatus.OK, returnedLoan);
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long loanId) {
        loanUseCase.deleteLoan(loanId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{loanId}")
    public ApiResponse<LoanResponseDTO> getLoanById(@PathVariable Long loanId) {
        Optional<LoanResponseDTO> loan = loanUseCase.getLoanById(loanId);
        return loan.map(l -> new ApiResponse<>(HttpStatus.OK.value(), "Loan found", HttpStatus.OK, l))
                .orElseGet(() -> new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Loan not found", HttpStatus.NOT_FOUND, null));
    }

    @PreAuthorize("hasAuthority('librarian')")
    @GetMapping("/all")
    public ApiResponse<List<LoanResponseDTO>> getAllLoans() {
        List<LoanResponseDTO> loans = loanUseCase.getAllLoans();
        return new ApiResponse<>(HttpStatus.OK.value(), "Loans found", HttpStatus.OK, loans);
    }

    @PreAuthorize("hasAuthority('librarian')")
    @GetMapping()
    public PaginatedResponse<List<LoanResponseDTO>> getAllLoans(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<LoanResponseDTO> loanPage = loanUseCase.getAllLoans(pageable);
        return new PaginatedResponse<>(
                HttpStatus.OK.value(), "Success", HttpStatus.OK, loanPage.getContent(),
                loanPage.getPageable(), loanPage.isLast(), loanPage.isFirst(),
                loanPage.hasNext(), loanPage.hasPrevious(), loanPage.getTotalPages(),
                (int) loanPage.getTotalElements());
    }

    @GetMapping("/user/{username}")
    public ApiResponse<List<LoanResponseDTO>> getLoansByUsername(@PathVariable String username) {
        List<LoanResponseDTO> loans = loanUseCase.getLoansByUsername(username);
        return new ApiResponse<>(HttpStatus.OK.value(), "Loans found", HttpStatus.OK, loans);
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<LoanResponseDTO>> getLoansByStatus(@PathVariable LoanStatus status) {
        List<LoanResponseDTO> loans = loanUseCase.getLoansByStatus(status);
        return new ApiResponse<>(HttpStatus.OK.value(), "Loans found", HttpStatus.OK, loans);
    }
}
