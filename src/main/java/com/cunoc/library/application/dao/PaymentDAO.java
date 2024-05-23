package com.cunoc.library.application.dao;

import com.cunoc.library.adapters.out.entities.PaymentEntity;
import com.cunoc.library.application.dto.PaymentDTO;
import com.cunoc.library.application.dto.PaymentResponseDTO;
import com.cunoc.library.domain.models.enums.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PaymentDAO {
    Optional<PaymentEntity> find(Long id);
    Optional<PaymentResponseDTO> findById(Long id);
    List<PaymentResponseDTO> findAll();
    Page<PaymentResponseDTO> findAll(Pageable pageable);
    PaymentResponseDTO save(PaymentDTO paymentDTO);
    PaymentResponseDTO updateStatus(Long id, PaymentStatus status);
    void deleteById(Long id);
    List<PaymentResponseDTO> findPaymentsByStatus(PaymentStatus status);
    List<PaymentResponseDTO> findPaymentsByLoanId(Long loanId);
}
