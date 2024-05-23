package com.cunoc.library.adapters.in.web;

import com.cunoc.library.application.dto.ReservationDTO;
import com.cunoc.library.application.dto.ReservationResponseDTO;
import com.cunoc.library.application.payload.ApiResponse;
import com.cunoc.library.application.payload.PaginatedResponse;
import com.cunoc.library.application.usecases.ReservationUseCase;
import com.cunoc.library.domain.models.enums.ReservationStatus;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationUseCase reservationUseCase;

    @PostMapping
    public ApiResponse<ReservationResponseDTO> createReservation(@RequestBody @Valid ReservationDTO reservationDTO) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", HttpStatus.OK, reservationUseCase.createReservation(reservationDTO));
    }

    @GetMapping("/{id}")
    public ApiResponse<Optional<ReservationResponseDTO>> getReservationById(@PathVariable Long id) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", HttpStatus.OK, reservationUseCase.getReservationById(id));
    }

    @GetMapping("/all")
    public ApiResponse<List<ReservationResponseDTO>> getAllReservations() {
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", HttpStatus.OK, reservationUseCase.getAllReservations());
    }

    @GetMapping
    public PaginatedResponse<List<ReservationResponseDTO>> getAllReservations(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<ReservationResponseDTO> reservationPage = reservationUseCase.getAllReservations(pageable);
        return new PaginatedResponse<>(
                HttpStatus.OK.value(), "Success",
                HttpStatus.OK, reservationPage.getContent(),
                reservationPage.getPageable(),
                reservationPage.isLast(),
                reservationPage.isFirst(),
                reservationPage.hasNext(),
                reservationPage.hasPrevious(),
                reservationPage.getTotalPages(),
                (int) reservationPage.getTotalElements()
        );
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<ReservationResponseDTO>> getReservationsByStatus(@PathVariable ReservationStatus status) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", HttpStatus.OK, reservationUseCase.getReservationsByStatus(status));
    }

    @GetMapping("/user/{username}")
    public ApiResponse<List<ReservationResponseDTO>> getReservationsByUsername(@PathVariable String username) {
        List<ReservationResponseDTO> reservations = reservationUseCase.getReservationsByUsername(username);
        if (reservations.isEmpty()) {
            throw new ResourceNotFoundException("Reservation", "username", "El usuario no tiene reservaciones");
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", HttpStatus.OK, reservations);
    }


    @PreAuthorize("hasAuthority('student')")
    @PutMapping("/cancel/{id}")
    public ApiResponse<Void> cancelReservation(@PathVariable Long id) {
        reservationUseCase.cancelReservation(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", HttpStatus.OK, null);
    }
}
