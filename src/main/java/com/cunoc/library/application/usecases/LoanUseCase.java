package com.cunoc.library.application.usecases;

import com.cunoc.library.application.dao.BookDAO;
import com.cunoc.library.application.dao.LoanDAO;
import com.cunoc.library.application.dao.PaymentDAO;
import com.cunoc.library.application.dto.*;
import com.cunoc.library.domain.models.enums.BookStatus;
import com.cunoc.library.domain.models.enums.LoanStatus;
import com.cunoc.library.domain.models.enums.PaymentStatus;
import com.cunoc.library.infraestructure.exceptions.BadRequestException;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class LoanUseCase {

    private final LoanDAO loanDAO;
    private final PaymentDAO paymentDAO;
    private final BookDAO bookDAO;

    public Optional<LoanResponseDTO> getLoanById(Long id) {
        return loanDAO.findById(id);
    }

    public List<LoanResponseDTO> getAllLoans() {
        return loanDAO.findAll();
    }

    public Page<LoanResponseDTO> getAllLoans(Pageable pageable) {
        return loanDAO.findAll(pageable);
    }

    @Transactional
    public LoanResponseDTO createLoan(LoanDTO loanDTO) {
        if (loanDAO.findActiveLoansByUsername(loanDTO.username()).size() >= 3) {
            throw new BadRequestException("The user already has the maximum number of active loans.");
        }

        BookResponseDTO book = bookDAO.findByIsbnCode(loanDTO.isbn_code())
                .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn_code", loanDTO.isbn_code()));

        if (book.quantity() <= 0) {
            throw new BadRequestException("The book is not available for loan.");
        }

        BookUpdateDTO bookUpdateDTO = new BookUpdateDTO(
                book.title(),
                book.author(),
                book.quantity() - 1,
                book.description(),
                book.cover_image(),
                book.publication_date(),
                book.editorial(),
                book.quantity() == 1 ? BookStatus.not_available : book.status()
        );

        bookDAO.update(book.isbn_code(), bookUpdateDTO);

        return loanDAO.save(loanDTO);
    }

    @Transactional
    public LoanResponseDTO returnLoan(Long loanId, Date returnDate) {
        LoanResponseDTO loan = loanDAO.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", loanId));

        long diffInMillies = Math.abs(returnDate.getTime() - loan.loan_date().getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        double loanFee = 5.0 * Math.min(diff, 3); // Only charge for the first 3 days
        double penaltyFee = 0.0;
        if (diff > 3) {
            penaltyFee = 15.0 * (diff - 3);
        }
        double totalAmount = loanFee + penaltyFee;

        PaymentDTO paymentDTO = new PaymentDTO(
                loan.id(),
                totalAmount,
                returnDate
        );

        PaymentResponseDTO payment = paymentDAO.save(paymentDTO);
        loanDAO.updateStatus(loan.id(), LoanStatus.returned);
        loanDAO.updateReturnDate(loan.id(), returnDate); // Ensure return date is updated

        BookResponseDTO book = bookDAO.findByIsbnCode(loan.isbn_code())
                .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn_code", loan.isbn_code()));

        BookUpdateDTO bookUpdateDTO = new BookUpdateDTO(
                book.title(),
                book.author(),
                book.quantity() + 1,
                book.description(),
                book.cover_image(),
                book.publication_date(),
                book.editorial(),
                BookStatus.available
        );

        bookDAO.update(book.isbn_code(), bookUpdateDTO);

        return loanDAO.findById(loan.id()).orElseThrow(() -> new ResourceNotFoundException("Loan", "id", loan.id()));
    }
    public void deleteLoan(Long id) {
        loanDAO.deleteById(id);
    }

    public List<LoanResponseDTO> getLoansByUsername(String username) {
        return loanDAO.findLoansByUsername(username);
    }

    public List<LoanResponseDTO> getLoansByStatus(LoanStatus status) {
        return loanDAO.findLoansByStatus(status);
    }

    @Transactional
    public void checkLoansInDefault() {

        List<LoanResponseDTO> loans = loanDAO.findLoansByStatus(LoanStatus.lent);
        Date now = new Date();

        for (LoanResponseDTO loan : loans) {
            System.out.println("Loan: " + loan.id() + ", Loan date: " + loan.loan_date());
            long diffInMillies = Math.abs(now.getTime() - loan.loan_date().getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            System.out.println("Loan: " + loan.id() + ", Days in default: " + diff);
            if (diff > 30) {
                loanDAO.updateStatus(loan.id(), LoanStatus.in_default);
                PaymentDTO paymentDTO = new PaymentDTO(
                        loan.id(),
                        150.0, // Fixed penalty for loans in default for more than 30 days
                        now
                );
                System.out.println("Payment: " + paymentDTO);
                paymentDAO.save(paymentDTO);
            }
        }
    }
}
