package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.adapters.out.entities.CareerEntity;
import com.cunoc.library.adapters.out.repositories.CareerRepository;
import com.cunoc.library.application.dao.CareerDAO;
import com.cunoc.library.application.dao.UserDAO;
import com.cunoc.library.adapters.out.repositories.UserRepository;
import com.cunoc.library.application.dto.RegisterDTO;
import com.cunoc.library.application.dto.UserResponseDTO;
import com.cunoc.library.application.dto.UserUpdateDTO;
import com.cunoc.library.domain.models.User;
import com.cunoc.library.domain.models.Carreer;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserDAOAdapter implements UserDAO {

    private final UserRepository userRepository;
    private final CareerRepository careerRepository;

    @Override
    public Optional<UserEntity> find(String username) {
        return userRepository.findById(username);
    }

    @Override
    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<UserResponseDTO> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).map(this::mapToResponseDTO);
    }

    @Override
    public List<UserResponseDTO> getUserByRoleAndName(String roleName, String name) {
        return userRepository.getUserByRoleAndName(roleName, name)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> getUsersByRole(String roleName) {
        return userRepository.getUsersByRole(roleName)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserResponseDTO> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::mapToResponseDTO);
    }

    @Override
    public UserResponseDTO saveUser(RegisterDTO userDTO, PasswordEncoder passwordEncoder) {


        CareerEntity carreerEntity = null;
        if (userDTO.career_code() != null && !userDTO.career_code().isEmpty()) {
            carreerEntity = careerRepository.findById(userDTO.career_code())
                    .orElseThrow(() ->  new ResourceNotFoundException("career", "career_code", userDTO.career_code()));
        }

        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.username())
                .fullName(userDTO.full_name())
                .career(carreerEntity) // Set the carreer entity if it exists
                .role(userDTO.role())
                .dob(userDTO.dob())
                .password(passwordEncoder.encode(userDTO.password()))
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        userRepository.save(userEntity);
        return mapToResponseDTO(userEntity);
    }

    @Override
    public UserResponseDTO updateUser(UserEntity userEntity, UserUpdateDTO userUpdateDTO, PasswordEncoder passwordEncoder) {
        CareerEntity carreerEntity = null;
        if (userUpdateDTO.career_code() != null && !userUpdateDTO.career_code().isEmpty()) {
            carreerEntity = careerRepository.findById(userUpdateDTO.career_code())
                    .orElseThrow(() ->  new ResourceNotFoundException("career", "career_code", userUpdateDTO.career_code()));
        }

        userEntity = UserEntity.builder()
                .username(userEntity.getUsername())
                .fullName(userUpdateDTO.full_name())
                .career(carreerEntity) // Set the carreer entity if it exists
                .role(userUpdateDTO.role())
                .dob(userUpdateDTO.dob())
                .password(passwordEncoder.encode(userUpdateDTO.password()))
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        userRepository.save(userEntity);
        return mapToResponseDTO(userEntity);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    private UserResponseDTO mapToResponseDTO(UserEntity userEntity) {
        return new UserResponseDTO(
                userEntity.getUsername(),
                userEntity.getFullName(),
                userEntity.getCareer() != null ? userEntity.getCareer().getCode() : null,
                userEntity.getRole(),
                userEntity.getDob(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
        );
    }

    private UserEntity mapToEntity(RegisterDTO userDTO, PasswordEncoder passwordEncoder) {
        CareerEntity careerEntity = null;
        if (userDTO.career_code() != null && !userDTO.career_code().isEmpty()) {
            careerEntity = careerRepository.findById(userDTO.career_code())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid career code: " + userDTO.career_code()));
        }

        return UserEntity.builder()
                .username(userDTO.username())
                .fullName(userDTO.full_name())
                .career(careerEntity)
                .role(userDTO.role())
                .dob(userDTO.dob())
                .password(passwordEncoder.encode(userDTO.password()))
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }
}
