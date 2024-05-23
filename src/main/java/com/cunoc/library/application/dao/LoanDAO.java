package com.cunoc.library.application.dao;

import com.cunoc.library.adapters.out.entities.LoanEntity;
import com.cunoc.library.application.dto.LoanDTO;
import com.cunoc.library.application.dto.LoanResponseDTO;
import com.cunoc.library.domain.models.enums.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LoanDAO {
    Optional<LoanEntity> find(Long id);
    Optional<LoanResponseDTO> findById(Long id);
    List<LoanResponseDTO> findAll();
    Page<LoanResponseDTO> findAll(Pageable pageable);
    LoanResponseDTO save(LoanDTO loanDTO);
    LoanResponseDTO updateStatus(Long id, LoanStatus status);
    void deleteById(Long id);
    List<LoanResponseDTO> findActiveLoansByUsername(String username);
    List<LoanResponseDTO> findLoansByStatus(LoanStatus status);
    List<LoanResponseDTO> findLoansByUsername(String username);
    boolean hasActiveLoans(String username);
    void updateReturnDate(Long id, Date returnDate);
}
