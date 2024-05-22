package com.cunoc.library.adapters.in.web;

import com.cunoc.library.application.dto.CareerDTO;
import com.cunoc.library.application.dto.CareerResponseDTO;
import com.cunoc.library.application.payload.ApiResponse;
import com.cunoc.library.application.usecases.CareerUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/career")
@RequiredArgsConstructor
@EnableMethodSecurity
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CareerController {

    private final CareerUseCase careerUseCase;

    @PreAuthorize("hasAuthority('librarian')")
    @PostMapping()
    public ApiResponse<CareerResponseDTO> saveCareer(@RequestBody @Valid CareerDTO request)
    {
        System.out.println("CareerController.saveCareer");
        System.out.println(request);
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,careerUseCase.save(request));
    }


}
