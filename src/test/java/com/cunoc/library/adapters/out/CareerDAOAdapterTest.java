package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.CareerEntity;
import com.cunoc.library.adapters.out.repositories.CareerRepository;
import com.cunoc.library.application.dto.CareerDTO;
import com.cunoc.library.application.dto.CareerResponseDTO;
import com.cunoc.library.application.dto.CareerUpdateDTO;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CareerDAOAdapterTest {

    @Mock
    private CareerRepository careerRepository;

    @InjectMocks
    private CareerDAOAdapter careerDAOAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFind() {
        CareerEntity careerEntity = new CareerEntity();
        careerEntity.setCode("CS");

        when(careerRepository.findById(anyString())).thenReturn(Optional.of(careerEntity));

        Optional<CareerEntity> result = careerDAOAdapter.find("CS");

        assertTrue(result.isPresent());
        assertEquals("CS", result.get().getCode());
    }

    @Test
    void testFindByCode() {
        CareerEntity careerEntity = new CareerEntity();
        careerEntity.setCode("CS");
        careerEntity.setName("Computer Science");

        when(careerRepository.findById(anyString())).thenReturn(Optional.of(careerEntity));

        Optional<CareerResponseDTO> result = careerDAOAdapter.findByCode("CS");

        assertTrue(result.isPresent());
        assertEquals("CS", result.get().code());
        assertEquals("Computer Science", result.get().name());
    }

    @Test
    void testFindAll() {
        CareerEntity careerEntity = new CareerEntity();
        careerEntity.setCode("CS");

        when(careerRepository.findAll()).thenReturn(Collections.singletonList(careerEntity));

        List<CareerResponseDTO> result = careerDAOAdapter.findAll();

        assertEquals(1, result.size());
        assertEquals("CS", result.get(0).code());
    }

    @Test
    void testFindAllPageable() {
        CareerEntity careerEntity = new CareerEntity();
        careerEntity.setCode("CS");

        Page<CareerEntity> careerPage = new PageImpl<>(Collections.singletonList(careerEntity));
        when(careerRepository.findAll(any(Pageable.class))).thenReturn(careerPage);

        Page<CareerResponseDTO> result = careerDAOAdapter.findAll(Pageable.unpaged());

        assertEquals(1, result.getContent().size());
        assertEquals("CS", result.getContent().get(0).code());
    }

    @Test
    void testSave() {
        CareerDTO careerDTO = new CareerDTO("CS", "Computer Science");

        CareerEntity careerEntity = new CareerEntity();
        careerEntity.setCode("CS");

        when(careerRepository.save(any(CareerEntity.class))).thenReturn(careerEntity);

        CareerResponseDTO result = careerDAOAdapter.save(careerDTO);

        assertEquals("CS", result.code());
    }

    @Test
    void testUpdate() {
        CareerEntity careerEntity = new CareerEntity();
        careerEntity.setCode("CS");
        careerEntity.setCreatedAt(new Date());

        CareerUpdateDTO careerUpdateDTO = new CareerUpdateDTO("Updated Computer Science");

        when(careerRepository.findById(anyString())).thenReturn(Optional.of(careerEntity));
        when(careerRepository.save(any(CareerEntity.class))).thenReturn(careerEntity);

        CareerResponseDTO result = careerDAOAdapter.update("CS", careerUpdateDTO);

        assertEquals("CS", result.code());

    }

    @Test
    void testDeleteById() {
        doNothing().when(careerRepository).deleteById(anyString());

        careerDAOAdapter.deleteById("CS");

        verify(careerRepository, times(1)).deleteById("CS");
    }

    @Test
    void testMapToResponseDTO() {
        CareerEntity careerEntity = new CareerEntity();
        careerEntity.setCode("CS");
        careerEntity.setName("Computer Science");
        careerEntity.setCreatedAt(new Date());
        careerEntity.setUpdatedAt(new Date());

        CareerResponseDTO responseDTO = careerDAOAdapter.mapToResponseDTO(careerEntity);

        assertEquals("CS", responseDTO.code());
        assertEquals("Computer Science", responseDTO.name());
    }
}
