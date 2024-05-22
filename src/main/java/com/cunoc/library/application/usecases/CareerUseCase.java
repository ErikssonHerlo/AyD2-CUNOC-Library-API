package com.cunoc.library.application.usecases;

import com.cunoc.library.adapters.out.CareerDAOAdapter;
import com.cunoc.library.application.dao.CareerDAO;
import com.cunoc.library.application.dto.CareerDTO;
import com.cunoc.library.application.dto.CareerResponseDTO;
import com.cunoc.library.infraestructure.exceptions.ResourceAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CareerUseCase {
    private final CareerDAO careerDAO;
    private final CareerDAOAdapter careerDAOAdapter;

    public Optional<CareerResponseDTO> findById(String code) {
        return careerDAO.find(code).map(careerDAOAdapter::mapToResponseDTO);
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

    public void deleteById(String code) {
        careerDAO.deleteById(code);
    }
}
