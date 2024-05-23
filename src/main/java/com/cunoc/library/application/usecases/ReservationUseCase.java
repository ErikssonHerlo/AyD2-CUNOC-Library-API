package com.cunoc.library.application.usecases;

import com.cunoc.library.application.dao.ReservationDAO;
import com.cunoc.library.application.dao.BookDAO;
import com.cunoc.library.application.dto.ReservationDTO;
import com.cunoc.library.application.dto.ReservationResponseDTO;
import com.cunoc.library.domain.models.enums.BookStatus;
import com.cunoc.library.domain.models.enums.ReservationStatus;
import com.cunoc.library.infraestructure.exceptions.BadRequestException;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
public class ReservationUseCase {

    private final ReservationDAO reservationDAO;
    private final BookDAO bookDAO;

    @Transactional
    public ReservationResponseDTO createReservation(ReservationDTO reservationDTO) {
        try {
            var book = bookDAO.findByIsbnCode(reservationDTO.isbn_code())
                    .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn_code", reservationDTO.isbn_code()));

            if (book.status() == BookStatus.available) {
                throw new BadRequestException("El libro ya está disponible, no se puede hacer una reserva.");
            }

            ReservationResponseDTO reservationResponse = reservationDAO.save(reservationDTO);
            scheduleReservationExpiry(reservationResponse);
            return reservationResponse;
        } catch (Exception e) {
            throw new BadRequestException("Error al crear la reservación: " + e.getMessage());
        }
    }

    public Optional<ReservationResponseDTO> getReservationById(Long id) {
        try {
            return reservationDAO.findById(id);
        } catch (Exception e) {
            throw new BadRequestException("Error al obtener la reservación: " + e.getMessage());
        }
    }

    public List<ReservationResponseDTO> getAllReservations() {
        try {
            return reservationDAO.findAll();
        } catch (Exception e) {
            throw new BadRequestException("Error al obtener todas las reservaciones: " + e.getMessage());
        }
    }

    public Page<ReservationResponseDTO> getAllReservations(Pageable pageable) {
        try {
            return reservationDAO.findAll(pageable);
        } catch (Exception e) {
            throw new BadRequestException("Error al obtener las reservaciones paginadas: " + e.getMessage());
        }
    }

    public List<ReservationResponseDTO> getReservationsByStatus(ReservationStatus status) {
        try {
            return reservationDAO.getReservationsByStatus(status);
        } catch (Exception e) {
            throw new BadRequestException("Error al obtener las reservaciones por estado: " + e.getMessage());
        }
    }

    public List<ReservationResponseDTO> getReservationsByUsername(String username) {
        try {
            List<ReservationResponseDTO> reservations = reservationDAO.getReservationsByUsername(username);
            if (reservations.isEmpty()) {
                throw new ResourceNotFoundException("Reservation", "username", "El usuario no tiene reservaciones");
            }
            return reservations;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Transactional
    public void cancelReservation(Long id) {
        try {
            var reservation = reservationDAO.find(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
            reservationDAO.updateStatus(id, ReservationStatus.canceled);
        } catch (Exception e) {
            throw new BadRequestException("Error al cancelar la reservación: " + e.getMessage());
        }
    }

    public void notifyUserOfAvailableBook(Long reservationId) {
        try {
            var reservation = reservationDAO.find(reservationId)
                    .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));

            var book = bookDAO.findByIsbnCode(reservation.getBook().getISBNCode())
                    .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn_code", reservation.getBook().getISBNCode()));

            if (book.status() == BookStatus.available) {

                // Por simplicidad, vamos a imprimir un mensaje en la consola.
                System.out.println("Notificando al usuario " + reservation.getUser().getUsername() + " que el libro " + reservation.getBook().getISBNCode() + " está disponible.");
            }
        } catch (Exception e) {
            throw new BadRequestException("Error al notificar al usuario: " + e.getMessage());
        }
    }

    public void checkAndNotifyUsersForAvailableBooks() {
        try {
            List<ReservationResponseDTO> activeReservations = reservationDAO.getReservationsByStatus(ReservationStatus.active);
            for (ReservationResponseDTO reservation : activeReservations) {
                notifyUserOfAvailableBook(reservation.id());
            }
        } catch (Exception e) {
            throw new BadRequestException("Error al verificar y notificar a los usuarios: " + e.getMessage());
        }
    }

    public void expireReservationsForAvailableBooks() {
        try {
            List<ReservationResponseDTO> activeReservations = reservationDAO.getReservationsByStatus(ReservationStatus.active);
            for (ReservationResponseDTO reservation : activeReservations) {
                var book = bookDAO.findByIsbnCode(reservation.isbn_code())
                        .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn_code", reservation.isbn_code()));

                if (book.status() == BookStatus.available && new Date().getTime() - reservation.reservation_date().getTime() > 180000) {
                    // 180000ms = 3 minutos
                    // Si la reserva lleva más de 3 minutos en estado activo, se considera expirada.
                    reservationDAO.updateStatus(reservation.id(), ReservationStatus.expired);
                    System.out.println("La reserva con ID " + reservation.id() + " ha expirado a las " + new Date().toString() + ".");
                }
            }
        } catch (Exception e) {
            throw new BadRequestException("Error al expirar las reservaciones: " + e.getMessage());
        }
    }

    private void scheduleReservationExpiry(ReservationResponseDTO reservationResponse) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                var book = bookDAO.findByIsbnCode(reservationResponse.isbn_code())
                        .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn_code", reservationResponse.isbn_code()));

                if (book.status() == BookStatus.available) {
                    reservationDAO.updateStatus(reservationResponse.id(), ReservationStatus.expired);
                    System.out.println("La reserva con ID " + reservationResponse.id() + " ha expirado.");
                }
            }
        }, new Date(System.currentTimeMillis() + 86400000)); // 86400000ms = 1 día
    }
}
