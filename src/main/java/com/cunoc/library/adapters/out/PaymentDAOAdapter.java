package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.LoanEntity;
import com.cunoc.library.adapters.out.entities.PaymentEntity;
import com.cunoc.library.adapters.out.repositories.LoanRepository;
import com.cunoc.library.adapters.out.repositories.PaymentRepository;
import com.cunoc.library.application.dao.PaymentDAO;
import com.cunoc.library.application.dto.PaymentDTO;
import com.cunoc.library.application.dto.PaymentResponseDTO;
import com.cunoc.library.domain.models.enums.PaymentStatus;
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
public class PaymentDAOAdapter implements PaymentDAO {

    private final PaymentRepository paymentRepository;
    private final LoanRepository loanRepository;

    @Override
    public Optional<PaymentEntity> find(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Optional<PaymentResponseDTO> findById(Long id) {
        return paymentRepository.findById(id).map(this::mapToResponseDTO);
    }

    @Override
    public List<PaymentResponseDTO> findAll() {
        return paymentRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PaymentResponseDTO> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable).map(this::mapToResponseDTO);
    }

    @Transactional
    @Override
    public PaymentResponseDTO save(PaymentDTO paymentDTO) {
        LoanEntity loanEntity = loanRepository.findById(paymentDTO.loan_id())
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", paymentDTO.loan_id()));

        PaymentEntity paymentEntity = PaymentEntity.builder()
                .loan(loanEntity)
                .paymentAmount(paymentDTO.payment_amount())
                .paymentDate(paymentDTO.payment_date())
                .defaultAmount(0.0) // Calcular según la lógica de negocio
                .registerDate(new Date())
                .totalAmount(paymentDTO.payment_amount()) // Calcular según la lógica de negocio
                .status(PaymentStatus.pending)
                .build();

        paymentRepository.save(paymentEntity);
        return mapToResponseDTO(paymentEntity);
    }

    @Transactional
    @Override
    public PaymentResponseDTO updateStatus(Long id, PaymentStatus status) {
        PaymentEntity paymentEntity = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", "id", id));
        paymentEntity.setStatus(status);
        paymentEntity.setRegisterDate(new Date());
        paymentRepository.save(paymentEntity);
        return mapToResponseDTO(paymentEntity);
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentResponseDTO> findPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findAllByStatus(status)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDTO> findPaymentsByLoanId(Long loanId) {
        return paymentRepository.findAllByLoanId(loanId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private PaymentResponseDTO mapToResponseDTO(PaymentEntity paymentEntity) {
        return new PaymentResponseDTO(
                paymentEntity.getId(),
                paymentEntity.getLoan().getId(),
                paymentEntity.getPaymentAmount(),
                paymentEntity.getPaymentDate(),
                paymentEntity.getDefaultAmount(),
                paymentEntity.getRegisterDate(),
                paymentEntity.getTotalAmount(),
                paymentEntity.getStatus().name()
        );
    }
}
