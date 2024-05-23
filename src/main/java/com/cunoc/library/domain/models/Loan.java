package com.cunoc.library.domain.models;

import com.cunoc.library.adapters.out.entities.LoanEntity;
import com.cunoc.library.domain.models.enums.LoanStatus;

import java.util.Date;

public record Loan(
        Long id,
        Book book,
        User user,
        Date loanDate,
        Date returnDate,
        LoanStatus status,
        Date createdAt,
        Date updatedAt
) {
    public Loan(LoanEntity loanEntity) {
        this(
                loanEntity.getId(),
                new Book(loanEntity.getBook()),
                new User(loanEntity.getUser()),
                loanEntity.getLoanDate(),
                loanEntity.getReturnDate(),
                loanEntity.getStatus(),
                loanEntity.getCreatedAt(),
                loanEntity.getUpdatedAt()
        );
    }
}
