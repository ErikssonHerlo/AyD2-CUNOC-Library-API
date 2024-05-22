package com.cunoc.library.adapters.in.web;

import com.cunoc.library.application.dto.CareerDTO;
import com.cunoc.library.application.dto.CareerResponseDTO;
import com.cunoc.library.application.dto.CareerUpdateDTO;
import com.cunoc.library.application.payload.ApiResponse;
import com.cunoc.library.application.payload.PaginatedResponse;
import com.cunoc.library.application.usecases.CareerUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,careerUseCase.save(request));
    }

    @PreAuthorize("hasAuthority('librarian')")
    @GetMapping("/{code}")
    public ApiResponse<CareerResponseDTO> getCareerByCode(@PathVariable String code)
    {
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,careerUseCase.findById(code));
    }


    @PreAuthorize("hasAuthority('librarian')")
    @GetMapping()
    public PaginatedResponse<List<CareerResponseDTO>> getAllCareers(@PageableDefault(page = 0,size = 10) Pageable pageable)
    {
        Page<CareerResponseDTO> careerPage = careerUseCase.findAll(pageable);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"Success", HttpStatus.OK,careerPage.getContent(),careerPage.getPageable());
    }

    @PreAuthorize("hasAuthority('librarian')")
    @PutMapping("/{code}")
    public ApiResponse<CareerResponseDTO> updateCareer(@PathVariable String code, @RequestBody @Valid CareerUpdateDTO request)
    {
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,careerUseCase.update(code,request));
    }

    @PreAuthorize("hasAuthority('librarian')")
    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteCareer(@PathVariable String code)
    {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(careerUseCase.deleteById(code));
    }

}
