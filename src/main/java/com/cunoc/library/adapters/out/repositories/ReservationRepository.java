package com.cunoc.library.adapters.out.repositories;

import com.cunoc.library.adapters.out.entities.ReservationEntity;
import com.cunoc.library.domain.models.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query("SELECT r FROM ReservationEntity r WHERE r.status = :status")
    List<ReservationEntity> findByStatus(@Param("status") ReservationStatus status);

    @Query("SELECT r FROM ReservationEntity r WHERE r.user.username = :username")
    List<ReservationEntity> findByUsername(@Param("username") String username);
}
