package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.application.dao.UserDAO;
import com.cunoc.library.adapters.out.repositories.UserRepository;
import com.cunoc.library.application.dto.RegisterDTO;
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
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        Optional<UserEntity> userEntityOptional = userRepository.findUserByUsername(username);
        return userEntityOptional.map(this::mapToDomain
        );
    }

    @Override
    public List<User> getUserByRoleAndName(String roleName, String name) {
        return userRepository.getUserByRoleAndName(roleName, name)
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getUsersByRole(String roleName) {
        return userRepository.getUsersByRole(roleName)
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        Page<UserEntity> userEntitiesPage = userRepository.findAll(pageable);
        List<User> users = userEntitiesPage
                .map(userEntity ->
                    new User(
                        userEntity.getUsername(),
                        userEntity.getFullName(),
                        userEntity.getCarreer() != null ? new Carreer(userEntity.getCarreer()) : null,
                        userEntity.getRole(),
                        userEntity.getDob(),
                        userEntity.getPassword(),
                        userEntity.getCreatedAt(),
                        userEntity.getUpdatedAt(),
                        userEntity.getAuthorities()
                    )
                )
                .toList();

        return new PageImpl<>(users, pageable, userEntitiesPage.getTotalElements());
    }

    @Override
    public User saveUser(RegisterDTO userDTO, PasswordEncoder passwordEncoder) {

        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.username())
                .fullName(userDTO.full_name())
                .carreer(null) // Maneja esto según tu lógica de negocio
                .role(userDTO.role())
                .dob(userDTO.dob())
                .password(passwordEncoder.encode(userDTO.password()))
                .build();

        userRepository.save(userEntity);
        return new User(userEntity);
    }

    @Override
    public void updateUser(UserEntity userEntity, RegisterDTO input, PasswordEncoder passwordEncoder) {
        userEntity.setFullName(input.full_name());
        userEntity.setCarreer(null); // Maneja esto según tu lógica de negocio
        userEntity.setRole(input.role());
        userEntity.setDob(input.dob());
        userEntity.setPassword(passwordEncoder.encode(input.password()));
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    private User mapToDomain(UserEntity userEntity) {
        return new User(
                userEntity.getUsername(),
                userEntity.getFullName(),
                userEntity.getCarreer() != null ? new Carreer(userEntity.getCarreer()) : null,
                userEntity.getRole(),
                userEntity.getDob(),
                userEntity.getPassword(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt(),
                userEntity.getAuthorities()
        );
    }



}
