package com.cunoc.library.application.usecases;

import com.cunoc.library.application.dao.CareerDAO;
import com.cunoc.library.application.dto.RegisterDTO;
import com.cunoc.library.application.dto.UserResponseDTO;
import com.cunoc.library.application.dto.UserUpdateDTO;
import com.cunoc.library.domain.models.User;
import com.cunoc.library.application.dao.UserDAO;
import com.cunoc.library.infraestructure.exceptions.BadRequestException;
import com.cunoc.library.infraestructure.exceptions.ResourceAlreadyExistsException;
import com.cunoc.library.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserUseCase {
    private final UserDAO userDao;
    private final CareerDAO careerDAO;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserResponseDTO> getUserInfo(Authentication authentication){
        try{
            var user = userDao.findUserByUsername(authentication.getName());
            return user;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<UserResponseDTO> getAllUsers(){
        return new ArrayList<>(userDao.findAllUsers());
    }
    public Page<UserResponseDTO> getAllUsers(Pageable pageable){
        return userDao.findAllUsers(pageable);
    }

    public Optional<UserResponseDTO> getUserByUsername(String username){
        try{
            var user = userDao.findUserByUsername(username);
            if(!user.isPresent()) throw new ResourceNotFoundException("user","username",username);
            return user;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public UserResponseDTO saveUser(RegisterDTO request) {
        if(request.career_code()!= null && request.career_code().isEmpty())
        {
            var careerExists = careerDAO.find(request.career_code()).isPresent();
            if(careerExists) throw new ResourceNotFoundException("career", "career_code", request.career_code());
        }
        var isPresent = userDao.findUserByUsername(request.username()).isPresent();
        if(isPresent) throw new ResourceAlreadyExistsException("user","username",request.username());
        return userDao.saveUser(request,passwordEncoder);
    }

    public UserResponseDTO updateUser(String username, UserUpdateDTO updateUserDTO) throws ResourceNotFoundException {
        try{
            var existingUser = userDao.find(username);
            if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","username",username);
            return userDao.updateUser(existingUser.get(),updateUserDTO, passwordEncoder);

        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteUser(String username) throws ResourceNotFoundException {
        try{
            var existingUser = userDao.find(username);
            if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","username",username);
            userDao.deleteUser(username);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
