package com.cunoc.library.adapters.in.web;

import com.cunoc.library.application.dto.UserResponseDTO;
import com.cunoc.library.application.payload.ApiResponse;
import com.cunoc.library.application.usecases.UserUseCase;
import com.cunoc.library.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@EnableMethodSecurity
@CrossOrigin(origins = "", allowedHeaders = "")
public class UserController {
    private final UserUseCase userUseCase;


    @GetMapping("/info")
    public ApiResponse<UserResponseDTO> getUserInfo(Authentication authentication){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,userUseCase.getUserInfo(authentication));
    }

}
