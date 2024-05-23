package com.cunoc.library.adapters.out.entities;

import com.cunoc.library.domain.models.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "PAYMENT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private LoanEntity loan;

    @Column(name = "payment_amount", nullable = false)
    private Double paymentAmount;

    @Column(name = "payment_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Column(name = "default_amount")
    private Double defaultAmount;

    @Column(name = "register_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;
}
