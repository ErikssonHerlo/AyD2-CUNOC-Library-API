package com.cunoc.library.application.dao;

import com.cunoc.library.adapters.out.entities.CareerEntity;
import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.application.dto.CareerResponseDTO;
import com.cunoc.library.application.dto.CareerDTO;
import com.cunoc.library.application.dto.RegisterDTO;
import com.cunoc.library.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
public interface CareerDAO {
    Optional<CareerEntity> find(String code);
    Optional<CareerResponseDTO> findByCode(String code);
    List<CareerResponseDTO> findAll();
    Page<CareerResponseDTO> findAll(Pageable pageable);
    CareerResponseDTO save(CareerDTO career);
    CareerResponseDTO update(String code, CareerDTO career);
    void deleteById(String code);
}