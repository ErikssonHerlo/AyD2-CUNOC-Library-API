package com.cunoc.library.application.dao;

import com.cunoc.library.adapters.out.entities.ReservationEntity;
import com.cunoc.library.application.dto.ReservationDTO;
import com.cunoc.library.application.dto.ReservationResponseDTO;
import com.cunoc.library.domain.models.enums.ReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReservationDAO {
    Optional<ReservationEntity> find(Long id);
    Optional<ReservationResponseDTO> findById(Long id);
    List<ReservationResponseDTO> findAll();
    Page<ReservationResponseDTO> findAll(Pageable pageable);
    ReservationResponseDTO save(ReservationDTO reservation);
    ReservationResponseDTO updateStatus(Long id, ReservationStatus status);
    void deleteById(Long id);

    List<ReservationResponseDTO> getReservationsByStatus(ReservationStatus status);

    List<ReservationResponseDTO> getReservationsByUsername(String username);
}
