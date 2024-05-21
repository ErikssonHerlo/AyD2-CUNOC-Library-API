package com.cunoc.library.application.usecases;

import com.cunoc.library.adapters.out.repositories.UserRepository;

import com.cunoc.library.application.dto.LoginDTO;
import com.cunoc.library.application.payload.AuthResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import com.cunoc.library.domain.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthUseCase {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginDTO request) {
        System.out.println(request.username());
        System.out.println(request.password());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        UserDetails user =userRepository.findUserByUsername(request.username()).orElseThrow();
        System.out.println(user);
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

}
