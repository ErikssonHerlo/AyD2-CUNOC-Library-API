package com.cunoc.library.domain.models;

import com.cunoc.library.adapters.out.entities.PaymentEntity;
import com.cunoc.library.domain.models.enums.PaymentStatus;

import java.util.Date;

public record Payment(
        Long id,
        Loan loan,
        Double paymentAmount,
        Date paymentDate,
        Double defaultAmount,
        Date registerDate,
        Double totalAmount,
        PaymentStatus status
) {
    public Payment(PaymentEntity paymentEntity) {
        this(
                paymentEntity.getId(),
                new Loan(paymentEntity.getLoan()),
                paymentEntity.getPaymentAmount(),
                paymentEntity.getPaymentDate(),
                paymentEntity.getDefaultAmount(),
                paymentEntity.getRegisterDate(),
                paymentEntity.getTotalAmount(),
                paymentEntity.getStatus()
        );
    }
}
