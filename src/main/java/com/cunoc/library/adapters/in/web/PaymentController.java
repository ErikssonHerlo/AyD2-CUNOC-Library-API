package com.cunoc.library.adapters.in.web;

import com.cunoc.library.application.dto.PaymentDTO;
import com.cunoc.library.application.dto.PaymentResponseDTO;
import com.cunoc.library.application.payload.ApiResponse;
import com.cunoc.library.application.payload.PaginatedResponse;
import com.cunoc.library.application.usecases.PaymentUseCase;
import com.cunoc.library.domain.models.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
@EnableMethodSecurity
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {
    private final PaymentUseCase paymentUseCase;

    @PreAuthorize("hasAuthority('librarian')")
    @PostMapping()
    public ApiResponse<PaymentResponseDTO> createPayment(@RequestBody @Valid PaymentDTO paymentDTO) {
        PaymentResponseDTO newPayment = paymentUseCase.createPayment(paymentDTO);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Payment created successfully", HttpStatus.CREATED, newPayment);
    }

    @PutMapping("/{paymentId}/status")
    public ApiResponse<PaymentResponseDTO> updatePaymentStatus(@PathVariable Long paymentId, @RequestParam PaymentStatus status) {
        PaymentResponseDTO updatedPayment = paymentUseCase.updatePaymentStatus(paymentId, status);
        return new ApiResponse<>(HttpStatus.OK.value(), "Payment status updated successfully", HttpStatus.OK, updatedPayment);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
        paymentUseCase.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{paymentId}")
    public ApiResponse<PaymentResponseDTO> getPaymentById(@PathVariable Long paymentId) {
        Optional<PaymentResponseDTO> payment = paymentUseCase.getPaymentById(paymentId);
        return payment.map(p -> new ApiResponse<>(HttpStatus.OK.value(), "Payment found", HttpStatus.OK, p))
                .orElseGet(() -> new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Payment not found", HttpStatus.NOT_FOUND, null));
    }

    @PreAuthorize("hasAuthority('librarian')")
    @GetMapping("/all")
    public ApiResponse<List<PaymentResponseDTO>> getAllPayments() {
        List<PaymentResponseDTO> payments = paymentUseCase.getAllPayments();
        return new ApiResponse<>(HttpStatus.OK.value(), "Payments found", HttpStatus.OK, payments);
    }

    @PreAuthorize("hasAuthority('librarian')")
    @GetMapping()
    public PaginatedResponse<List<PaymentResponseDTO>> getAllPayments(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<PaymentResponseDTO> paymentPage = paymentUseCase.getAllPayments(pageable);
        return new PaginatedResponse<>(
                HttpStatus.OK.value(), "Success", HttpStatus.OK, paymentPage.getContent(),
                paymentPage.getPageable(), paymentPage.isLast(), paymentPage.isFirst(),
                paymentPage.hasNext(), paymentPage.hasPrevious(), paymentPage.getTotalPages(),
                (int) paymentPage.getTotalElements());
    }

    @PreAuthorize("hasAuthority('librarian')")
    @GetMapping("/status/{status}")
    public ApiResponse<List<PaymentResponseDTO>> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        List<PaymentResponseDTO> payments = paymentUseCase.getPaymentsByStatus(status);
        return new ApiResponse<>(HttpStatus.OK.value(), "Payments found", HttpStatus.OK, payments);
    }


    @GetMapping("/loan/{loanId}")
    public ApiResponse<List<PaymentResponseDTO>> getPaymentsByLoanId(@PathVariable Long loanId) {
        List<PaymentResponseDTO> payments = paymentUseCase.getPaymentsByLoanId(loanId);
        return new ApiResponse<>(HttpStatus.OK.value(), "Payments found", HttpStatus.OK, payments);
    }
}
