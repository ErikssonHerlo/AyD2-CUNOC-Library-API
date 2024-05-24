package com.cunoc.library.adapters.out;

import com.cunoc.library.adapters.out.entities.CareerEntity;
import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.adapters.out.repositories.CareerRepository;
import com.cunoc.library.adapters.out.repositories.UserRepository;
import com.cunoc.library.application.dto.RegisterDTO;
import com.cunoc.library.application.dto.UserResponseDTO;
import com.cunoc.library.application.dto.UserUpdateDTO;
import com.cunoc.library.domain.models.enums.Role;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserDAOAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CareerRepository careerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserDAOAdapter userDAOAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFind() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user123");

        when(userRepository.findById(anyString())).thenReturn(Optional.of(userEntity));

        Optional<UserEntity> result = userDAOAdapter.find("user123");

        assertTrue(result.isPresent());
        assertEquals("user123", result.get().getUsername());
    }

    @Test
    void testFindAllUsers() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user123");

        when(userRepository.findAll()).thenReturn(Collections.singletonList(userEntity));

        List<UserResponseDTO> result = userDAOAdapter.findAllUsers();

        assertEquals(1, result.size());
        assertEquals("user123", result.get(0).username());
    }

    @Test
    void testFindUserByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user123");
        userEntity.setFullName("John Doe");
        userEntity.setRole(Role.student);
        userEntity.setCreatedAt(new Date());
        userEntity.setUpdatedAt(new Date());

        when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.of(userEntity));

        Optional<UserResponseDTO> result = userDAOAdapter.findUserByUsername("user123");

        assertTrue(result.isPresent());
        assertEquals("user123", result.get().username());
        assertEquals("John Doe", result.get().full_name());
    }

    @Test
    void testGetUserByRoleAndName() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user123");
        userEntity.setFullName("John Doe");

        when(userRepository.getUserByRoleAndName(anyString(), anyString()))
                .thenReturn(Collections.singletonList(userEntity));

        List<UserResponseDTO> result = userDAOAdapter.getUserByRoleAndName("student", "John");

        assertEquals(1, result.size());
        assertEquals("user123", result.get(0).username());
        assertEquals("John Doe", result.get(0).full_name());
    }

    @Test
    void testGetUsersByRole() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user123");

        when(userRepository.getUsersByRole(anyString()))
                .thenReturn(Collections.singletonList(userEntity));

        List<UserResponseDTO> result = userDAOAdapter.getUsersByRole("student");

        assertEquals(1, result.size());
        assertEquals("user123", result.get(0).username());
    }

    @Test
    void testFindAllUsersPageable() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user123");

        Page<UserEntity> userPage = new PageImpl<>(Collections.singletonList(userEntity));
        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);

        Page<UserResponseDTO> result = userDAOAdapter.findAllUsers(Pageable.unpaged());

        assertEquals(1, result.getContent().size());
        assertEquals("user123", result.getContent().get(0).username());
    }

    @Test
    void testSaveUser() {
        RegisterDTO registerDTO = new RegisterDTO(
                "user123",
                "John Doe",
                "CS",
                Role.student,
                "2000-01-01",
                "password123"
        );

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user123");
        userEntity.setFullName("John Doe");
        userEntity.setRole(Role.student);
        userEntity.setCreatedAt(new Date());
        userEntity.setUpdatedAt(new Date());

        CareerEntity careerEntity = new CareerEntity();
        careerEntity.setCode("CS");

        when(careerRepository.findById(anyString())).thenReturn(Optional.of(careerEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        UserResponseDTO result = userDAOAdapter.saveUser(registerDTO, passwordEncoder);

        assertEquals("user123", result.username());
        assertEquals("John Doe", result.full_name());
    }

    @Test
    void testUpdateUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user123");
        userEntity.setFullName("John Doe");
        userEntity.setRole(Role.student);

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(
                "Jane Doe",
                "CS",
                Role.student,
                "2000-01-01",
                "newpassword123"
        );

        CareerEntity careerEntity = new CareerEntity();
        careerEntity.setCode("CS");

        when(careerRepository.findById(anyString())).thenReturn(Optional.of(careerEntity));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserResponseDTO result = userDAOAdapter.updateUser(userEntity, userUpdateDTO, passwordEncoder);

        assertEquals("user123", result.username());
        assertEquals("Jane Doe", result.full_name());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(anyString());

        userDAOAdapter.deleteUser("user123");

        verify(userRepository, times(1)).deleteById(anyString());
    }

    @Test
    void testExistsByCareerCode() {
        when(userRepository.existsByCareerCode(anyString())).thenReturn(true);

        boolean result = userDAOAdapter.existsByCareerCode("CS");

        assertTrue(result);
    }
}
