package com.cunoc.library.domain.models;

import com.cunoc.library.adapters.out.entities.ReservationEntity;
import com.cunoc.library.domain.models.enums.ReservationStatus;

import java.util.Date;

public record Reservation(
        Long id,
        User user,
        Book book,
        Date reservationDate,
        ReservationStatus status,
        Date createdAt,
        Date updatedAt
) {
    public Reservation(ReservationEntity reservationEntity) {
        this(
                reservationEntity.getId(),
                new User(reservationEntity.getUser()),
                new Book(reservationEntity.getBook()),
                reservationEntity.getReservationDate(),
                reservationEntity.getStatus(),
                reservationEntity.getCreatedAt(),
                reservationEntity.getUpdatedAt()
        );
    }
}
