package com.cunoc.library.adapters.out.entities;

import com.cunoc.library.domain.models.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "loan")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_ISBN_code", nullable = false)
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_username", nullable = false)
    private UserEntity user;

    @Column(name = "loan_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date loanDate;

    @Column(name = "return_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoanStatus status;

    @Column(name = "created_at", nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}

