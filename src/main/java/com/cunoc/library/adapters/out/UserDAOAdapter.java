package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.application.dao.UserDAO;
import com.cunoc.library.adapters.out.repositories.UserRepository;
import com.cunoc.library.application.dto.RegisterDTO;
import com.cunoc.library.application.dto.UserResponseDTO;
import com.cunoc.library.domain.models.User;
import com.cunoc.library.domain.models.Carreer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserDAOAdapter implements UserDAO {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> find(String username) {
        return userRepository.findById(username);
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
        /*
        CarreerEntity carreerEntity = null;
        if (userDTO.carreerCode() != null && !userDTO.carreerCode().isEmpty()) {
            carreerEntity = carreerRepository.findById(userDTO.carreerCode())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid carreer code: " + userDTO.carreerCode()));
        }*/

        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.username())
                .fullName(userDTO.full_name())
                .career(null) // Set the carreer entity if it exists
                .role(userDTO.role())
                .dob(userDTO.dob())
                .password(passwordEncoder.encode(userDTO.password()))
                .build();

        userRepository.save(userEntity);
        return mapToResponseDTO(userEntity);
    }

    @Override
    public UserResponseDTO updateUser(UserEntity userEntity, RegisterDTO input, PasswordEncoder passwordEncoder) {
        /*
        CarreerEntity carreerEntity = null;
        if (input.carreerCode() != null && !input.carreerCode().isEmpty()) {
            carreerEntity = carreerRepository.findById(input.carreerCode())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid carreer code: " + input.carreerCode()));
        }

         */

        userEntity.setFullName(input.full_name());
        userEntity.setCareer(null); // Set the carreer entity if it exists
        userEntity.setRole(input.role());
        userEntity.setDob(input.dob());
        userEntity.setPassword(passwordEncoder.encode(input.password()));
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
                null, // Asignar valor apropiado si se agrega campo last_name en UserEntity
                userEntity.getCareer() != null ? userEntity.getCareer().getCode() : null,
                userEntity.getRole(),
                userEntity.getDob(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
        );
    }
}
