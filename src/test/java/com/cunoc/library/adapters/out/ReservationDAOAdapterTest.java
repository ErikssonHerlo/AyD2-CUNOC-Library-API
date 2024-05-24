package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.BookEntity;
import com.cunoc.library.adapters.out.entities.ReservationEntity;
import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.adapters.out.repositories.BookRepository;
import com.cunoc.library.adapters.out.repositories.ReservationRepository;
import com.cunoc.library.adapters.out.repositories.UserRepository;
import com.cunoc.library.application.dto.ReservationDTO;
import com.cunoc.library.application.dto.ReservationResponseDTO;
import com.cunoc.library.domain.models.enums.ReservationStatus;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ReservationDAOAdapterTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private ReservationDAOAdapter reservationDAOAdapter;

    private UserEntity userEntity;
    private BookEntity bookEntity;
    private ReservationEntity reservationEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userEntity = new UserEntity();
        userEntity.setUsername("testuser");

        bookEntity = new BookEntity();
        bookEntity.setISBNCode("12345");

        reservationEntity = new ReservationEntity();
        reservationEntity.setId(1L);
        reservationEntity.setUser(userEntity);
        reservationEntity.setBook(bookEntity);
        reservationEntity.setReservationDate(new Date());
        reservationEntity.setStatus(ReservationStatus.active);
        reservationEntity.setCreatedAt(new Date());
        reservationEntity.setUpdatedAt(new Date());
    }

    @Test
    void testFind() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservationEntity));

        Optional<ReservationEntity> result = reservationDAOAdapter.find(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindById() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservationEntity));

        Optional<ReservationResponseDTO> result = reservationDAOAdapter.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().id());
        assertEquals("testuser", result.get().username());
        assertEquals("12345", result.get().isbn_code());
    }

    @Test
    void testFindAll() {
        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservationEntity));

        List<ReservationResponseDTO> result = reservationDAOAdapter.findAll();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
    }

    @Test
    void testFindAllPageable() {
        Page<ReservationEntity> reservationPage = new PageImpl<>(Collections.singletonList(reservationEntity));
        when(reservationRepository.findAll(any(Pageable.class))).thenReturn(reservationPage);

        Page<ReservationResponseDTO> result = reservationDAOAdapter.findAll(Pageable.unpaged());

        assertEquals(1, result.getContent().size());
        assertEquals(1L, result.getContent().get(0).id());
    }

    @Test
    void testSave() {
        ReservationDTO reservationDTO = new ReservationDTO("testuser", "12345", new Date());

        when(userRepository.findById(anyString())).thenReturn(Optional.of(userEntity));
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(bookEntity));
        when(reservationRepository.save(any(ReservationEntity.class))).thenReturn(reservationEntity);

        ReservationResponseDTO result = reservationDAOAdapter.save(reservationDTO);

        assertNotNull(result);
    }

    @Test
    void testUpdateStatus() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservationEntity));
        when(reservationRepository.save(any(ReservationEntity.class))).thenReturn(reservationEntity);

        ReservationResponseDTO result = reservationDAOAdapter.updateStatus(1L, ReservationStatus.expired);

        assertNotNull(result);
        assertEquals(ReservationStatus.expired, result.status());
    }

    @Test
    void testDeleteById() {
        doNothing().when(reservationRepository).deleteById(anyLong());

        reservationDAOAdapter.deleteById(1L);

        verify(reservationRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetReservationsByStatus() {
        when(reservationRepository.findByStatus(any(ReservationStatus.class))).thenReturn(Collections.singletonList(reservationEntity));

        List<ReservationResponseDTO> result = reservationDAOAdapter.getReservationsByStatus(ReservationStatus.active);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
    }

    @Test
    void testGetReservationsByUsername() {
        when(reservationRepository.findByUsername(anyString())).thenReturn(Collections.singletonList(reservationEntity));

        List<ReservationResponseDTO> result = reservationDAOAdapter.getReservationsByUsername("testuser");

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
    }
}
