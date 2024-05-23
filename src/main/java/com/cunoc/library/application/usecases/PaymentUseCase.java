package com.cunoc.library.application.usecases;

import com.cunoc.library.application.dao.PaymentDAO;
import com.cunoc.library.application.dto.PaymentDTO;
import com.cunoc.library.application.dto.PaymentResponseDTO;
import com.cunoc.library.domain.models.enums.PaymentStatus;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentUseCase {

    private final PaymentDAO paymentDAO;

    public Optional<PaymentResponseDTO> getPaymentById(Long id) {
        return paymentDAO.findById(id);
    }

    public List<PaymentResponseDTO> getAllPayments() {
        return paymentDAO.findAll();
    }

    public Page<PaymentResponseDTO> getAllPayments(Pageable pageable) {
        return paymentDAO.findAll(pageable);
    }

    public PaymentResponseDTO createPayment(PaymentDTO paymentDTO) {
        return paymentDAO.save(paymentDTO);
    }

    @Transactional
    public PaymentResponseDTO updatePaymentStatus(Long id, PaymentStatus status) {
        return paymentDAO.updateStatus(id, status);
    }

    public void deletePayment(Long id) {
        paymentDAO.deleteById(id);
    }

    public List<PaymentResponseDTO> getPaymentsByStatus(PaymentStatus status) {
        return paymentDAO.findPaymentsByStatus(status);
    }

    public List<PaymentResponseDTO> getPaymentsByLoanId(Long loanId) {
        return paymentDAO.findPaymentsByLoanId(loanId);
    }
}
