package com.cunoc.library.application.jobs;

import com.cunoc.library.application.usecases.ReservationUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReservationScheduler {

    private final ReservationUseCase reservationUseCase;

    public ReservationScheduler(ReservationUseCase reservationUseCase) {
        this.reservationUseCase = reservationUseCase;
    }

    @Scheduled(cron = "0 */5 * * * ?") // Ejecuta cada 5 minutos
    // Para efectos de la prueba, se ejecutará cada 5 minutos
    public void checkExpiredReservations() {
        System.out.println("Job started checkExpiredReservations");
        reservationUseCase.expireReservationsForAvailableBooks();
        System.out.println("Job finished checkExpiredReservations");
    }

    @Scheduled(cron = "0 */5 * * * ?") // Ejecuta cada 5 minutos
    public void notifyUsersOfAvailableBooks() {
        System.out.println("Job started notifyUsersOfAvailableBooks");
        reservationUseCase.checkAndNotifyUsersForAvailableBooks();
        System.out.println("Job finished notifyUsersOfAvailableBooks");
    }
}
