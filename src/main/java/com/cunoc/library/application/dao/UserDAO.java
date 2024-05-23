package com.cunoc.library.application.dao;

import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.application.dto.RegisterDTO;
import com.cunoc.library.application.dto.UserResponseDTO;
import com.cunoc.library.application.dto.UserUpdateDTO;
import com.cunoc.library.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<UserEntity> find(String username);
     Optional<UserResponseDTO> findUserByUsername(String username); // Cambiado a UserResponseDTO
    List<UserResponseDTO> getUserByRoleAndName(String roleName, String name);

    List<UserResponseDTO> findAllUsers();
    List<UserResponseDTO> getUsersByRole(String roleName);
    Page<UserResponseDTO> findAllUsers(Pageable pageable);
    UserResponseDTO saveUser(RegisterDTO user, PasswordEncoder passwordEncoder);
    UserResponseDTO updateUser(UserEntity user, UserUpdateDTO input, PasswordEncoder passwordEncoder);
    void deleteUser(String username);
}
