package com.cunoc.library.adapters.in.auth;

import com.cunoc.library.application.dto.LoginDTO;
import com.cunoc.library.application.payload.AuthResponse;
import com.cunoc.library.application.usecases.AuthUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "", allowedHeaders = "")
public class AuthController {

    private final AuthUseCase authUseCase;


    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginDTO request)
    {
        return ResponseEntity.ok(authUseCase.login(request));
    }
}
