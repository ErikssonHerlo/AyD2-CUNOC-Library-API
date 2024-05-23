package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.BookEntity;
import com.cunoc.library.adapters.out.entities.LoanEntity;
import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.adapters.out.repositories.BookRepository;
import com.cunoc.library.adapters.out.repositories.LoanRepository;
import com.cunoc.library.adapters.out.repositories.UserRepository;
import com.cunoc.library.application.dao.LoanDAO;
import com.cunoc.library.application.dto.LoanDTO;
import com.cunoc.library.application.dto.LoanResponseDTO;
import com.cunoc.library.domain.models.enums.LoanStatus;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LoanDAOAdapter implements LoanDAO {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<LoanEntity> find(Long id) {
        return loanRepository.findById(id);
    }

    @Override
    public Optional<LoanResponseDTO> findById(Long id) {
        return loanRepository.findById(id).map(this::mapToResponseDTO);
    }

    @Override
    public List<LoanResponseDTO> findAll() {
        return loanRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<LoanResponseDTO> findAll(Pageable pageable) {
        return loanRepository.findAll(pageable).map(this::mapToResponseDTO);
    }

    @Transactional
    @Override
    public LoanResponseDTO save(LoanDTO loanDTO) {
        BookEntity bookEntity = bookRepository.findById(loanDTO.isbn_code())
                .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn_code", loanDTO.isbn_code()));
        UserEntity userEntity = userRepository.findById(loanDTO.username())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", loanDTO.username()));

        LoanEntity loanEntity = LoanEntity.builder()
                .book(bookEntity)
                .user(userEntity)
                .loanDate(loanDTO.loan_date())
                .status(LoanStatus.lent)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        loanRepository.save(loanEntity);
        return mapToResponseDTO(loanEntity);
    }

    @Transactional
    @Override
    public LoanResponseDTO updateStatus(Long id, LoanStatus status) {
        LoanEntity loanEntity = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", id));
        loanEntity.setStatus(status);
        loanEntity.setUpdatedAt(new Date());
        loanRepository.save(loanEntity);
        return mapToResponseDTO(loanEntity);
    }

    @Override
    public void deleteById(Long id) {
        loanRepository.deleteById(id);
    }

    @Override
    public List<LoanResponseDTO> findActiveLoansByUsername(String username) {
        return loanRepository.findActiveLoansByUsername(username)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanResponseDTO> findLoansByStatus(LoanStatus status) {
        return loanRepository.findAllByStatus(status)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanResponseDTO> findLoansByUsername(String username) {
        return loanRepository.findLoansByUsername(username)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasActiveLoans(String username) {
        return loanRepository.existsByUserUsernameAndStatus(username, LoanStatus.lent);
    }

    @Override
    public void updateReturnDate(Long id, Date returnDate) {
        LoanEntity loanEntity = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", id));
        loanEntity.setReturnDate(returnDate);
        loanEntity.setUpdatedAt(new Date());
        loanRepository.save(loanEntity);
    }
    private LoanResponseDTO mapToResponseDTO(LoanEntity loanEntity) {
        return new LoanResponseDTO(
                loanEntity.getId(),
                loanEntity.getBook().getISBNCode(),
                loanEntity.getUser().getUsername(),
                loanEntity.getLoanDate(),
                loanEntity.getReturnDate(),
                loanEntity.getStatus().name(),
                loanEntity.getCreatedAt(),
                loanEntity.getUpdatedAt()
        );
    }
}
