package com.cunoc.library.application.usecases;

import com.cunoc.library.adapters.out.CareerDAOAdapter;
import com.cunoc.library.application.dao.CareerDAO;
import com.cunoc.library.application.dto.CareerDTO;
import com.cunoc.library.application.dto.CareerResponseDTO;
import com.cunoc.library.application.dto.CareerUpdateDTO;
import com.cunoc.library.infraestructure.exceptions.BadRequestException;
import com.cunoc.library.infraestructure.exceptions.ResourceAlreadyExistsException;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CareerUseCase {
    private final CareerDAO careerDAO;
    private final CareerDAOAdapter careerDAOAdapter;

    public Optional<CareerResponseDTO> findById(String code) {
        var career = careerDAO.findByCode(code);
        if(!career.isPresent()) throw new ResourceNotFoundException("Career", "code", code);
        return career;
    }

    public List<CareerResponseDTO> findAll() {
        return careerDAO.findAll();
    }

    public Page<CareerResponseDTO> findAll(Pageable pageable) {
        return careerDAO.findAll(pageable);
    }

    public CareerResponseDTO save(CareerDTO careerRequestDTO) {
        var is_exist = careerDAO.find(careerRequestDTO.code());
        if (is_exist.isPresent()) {
            throw new ResourceAlreadyExistsException("Career", "code", careerRequestDTO.code());
        }
        return careerDAO.save(careerRequestDTO);

    }

    public CareerResponseDTO update(String code, CareerUpdateDTO careerRequestDTO) {
        var career = careerDAO.find(code);
        if(!career.isPresent()) throw new ResourceNotFoundException("Career", "code", code);
        return careerDAO.update(code, careerRequestDTO);
    }

    public ResponseEntity<?> deleteById(String code) {
        try {
            var existingCareer = careerDAO.find(code);
            if(!existingCareer.isPresent()) throw new ResourceNotFoundException("Career", "code", code);
            careerDAO.deleteById(code);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
