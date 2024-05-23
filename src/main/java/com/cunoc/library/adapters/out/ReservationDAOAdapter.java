package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.BookEntity;
import com.cunoc.library.adapters.out.entities.ReservationEntity;
import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.adapters.out.repositories.BookRepository;
import com.cunoc.library.adapters.out.repositories.ReservationRepository;
import com.cunoc.library.adapters.out.repositories.UserRepository;
import com.cunoc.library.application.dao.ReservationDAO;
import com.cunoc.library.application.dto.ReservationDTO;
import com.cunoc.library.application.dto.ReservationResponseDTO;
import com.cunoc.library.domain.models.enums.ReservationStatus;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ReservationDAOAdapter implements ReservationDAO {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public Optional<ReservationEntity> find(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Optional<ReservationResponseDTO> findById(Long id) {
        return reservationRepository.findById(id).map(this::mapToResponseDTO);
    }

    @Override
    public List<ReservationResponseDTO> findAll() {
        return reservationRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ReservationResponseDTO> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable).map(this::mapToResponseDTO);
    }

    @Override
    public ReservationResponseDTO save(ReservationDTO reservationDTO) {
        ReservationEntity reservationEntity = mapToEntity(reservationDTO);
        reservationRepository.save(reservationEntity);
        return mapToResponseDTO(reservationEntity);
    }

    @Override
    public ReservationResponseDTO updateStatus(Long id, ReservationStatus status) {
        ReservationEntity reservationEntity = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        reservationEntity.setStatus(status);
        reservationEntity.setUpdatedAt(new Date());
        reservationRepository.save(reservationEntity);
        return mapToResponseDTO(reservationEntity);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<ReservationResponseDTO> getReservationsByStatus(ReservationStatus status) {
        return reservationRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationResponseDTO> getReservationsByUsername(String username) {
        return reservationRepository.findByUsername(username)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private ReservationResponseDTO mapToResponseDTO(ReservationEntity reservationEntity) {
        return new ReservationResponseDTO(
                reservationEntity.getId(),
                reservationEntity.getUser().getUsername(),
                reservationEntity.getBook().getISBNCode(),
                reservationEntity.getReservationDate(),
                reservationEntity.getStatus(),
                reservationEntity.getCreatedAt(),
                reservationEntity.getUpdatedAt()
        );
    }

    private ReservationEntity mapToEntity(ReservationDTO reservationDTO) {
        UserEntity userEntity = userRepository.findById(reservationDTO.username())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", reservationDTO.username()));
        BookEntity bookEntity = bookRepository.findById(reservationDTO.isbn_code())
                .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn_code", reservationDTO.isbn_code()));

        return ReservationEntity.builder()
                .user(userEntity)
                .book(bookEntity)
                .reservationDate(reservationDTO.reservation_date())
                .status(ReservationStatus.active) // Asignar estado inicial
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }
}
