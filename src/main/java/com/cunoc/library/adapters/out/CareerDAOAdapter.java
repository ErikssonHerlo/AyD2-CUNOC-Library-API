package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.CareerEntity;
import com.cunoc.library.adapters.out.repositories.CareerRepository;
import com.cunoc.library.application.dao.CareerDAO;
import com.cunoc.library.application.dto.CareerDTO;
import com.cunoc.library.application.dto.CareerResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CareerDAOAdapter implements CareerDAO {

    private final CareerRepository careerRepository;

    @Override
    public Optional<CareerEntity> find(String code) {
        return careerRepository.findById(code);
    }

    @Override
    public Optional<CareerResponseDTO> findByCode(String code) {
        return careerRepository.findById(code).map(this::mapToResponseDTO);
    }


    @Override
    public List<CareerResponseDTO> findAll() {
        return careerRepository.findAll().stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Page<CareerResponseDTO> findAll(Pageable pageable) {
        return careerRepository.findAll(pageable).map(this::mapToResponseDTO);
    }

    @Override
    public CareerResponseDTO save(CareerDTO careerDTO) {
        CareerEntity careerEntity = mapToEntity(careerDTO);
        return mapToResponseDTO(careerRepository.save(careerEntity));
    }

    @Override
    public CareerResponseDTO update(String code, CareerDTO careerDTO) {
        CareerEntity careerEntity = mapToEntity(careerDTO);
        careerEntity.setCode(code);
        return mapToResponseDTO(careerRepository.save(careerEntity));
    }

    @Override
    public void deleteById(String code) {
        careerRepository.deleteById(code);
    }

    public CareerResponseDTO mapToResponseDTO(CareerEntity careerEntity) {
        return new CareerResponseDTO(
                careerEntity.getCode(),
                careerEntity.getName(),
                careerEntity.getCreatedAt(),
                careerEntity.getUpdatedAt()
        );
    }

    private CareerEntity mapToEntity(CareerDTO careerDTO) {
        return CareerEntity.builder()
                .code(careerDTO.code())
                .name(careerDTO.name())
                .build();
    }
}
