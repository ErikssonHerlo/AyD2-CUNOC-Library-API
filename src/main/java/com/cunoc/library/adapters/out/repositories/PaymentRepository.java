package com.cunoc.library.adapters.out.repositories;

import com.cunoc.library.adapters.out.entities.PaymentEntity;
import com.cunoc.library.domain.models.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    @Query("SELECT p FROM PaymentEntity p WHERE p.status = :status")
    List<PaymentEntity> findAllByStatus(@Param("status") PaymentStatus status);

    @Query("SELECT p FROM PaymentEntity p WHERE p.loan.id = :loanId")
    List<PaymentEntity> findAllByLoanId(@Param("loanId") Long loanId);
}
