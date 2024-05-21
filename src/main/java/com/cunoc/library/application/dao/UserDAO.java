package com.cunoc.library.application.dao;

import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.application.dto.RegisterDTO;
import com.cunoc.library.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<UserEntity> find(String username);
    Optional<User> findUserByUsername(String username);
    List<User> getUserByRoleAndName(String roleName, String name);
    List<User> getUsersByRole(String roleName);
    Page<User> findAllUsers(Pageable pageable);
    User saveUser(RegisterDTO user, PasswordEncoder passwordEncoder);
    void updateUser(UserEntity user, RegisterDTO input, PasswordEncoder passwordEncoder);
    void deleteUser(String username);
}
