package com.cunoc.library.infraestructure.exceptions;

import com.cunoc.library.application.payload.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleMethodArgumentNotValidException() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError));
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException((MethodParameter) null, bindingResult);

        ResponseEntity<Object> responseEntity = globalExceptionHandler.handlerMethodArgumentNotValidException(ex, webRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isInstanceOf(ApiResponse.class);
        ApiResponse<?> apiResponse = (ApiResponse<?>) responseEntity.getBody();
        assertThat(apiResponse.getCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(apiResponse.getMessage()).contains("field=defaultMessage");
        assertThat(apiResponse.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testHandleBadRequestException() {
        BadRequestException ex = new BadRequestException("Bad request");

        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.handlerInternalErrorException(ex, webRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isInstanceOf(ApiResponse.class);
        ApiResponse<?> apiResponse = responseEntity.getBody();
        assertThat(apiResponse.getCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(apiResponse.getMessage()).isEqualTo("Bad request");
        assertThat(apiResponse.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testHandleException() {
        Exception ex = new Exception("Internal server error");

        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.handlerException(ex, webRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseEntity.getBody()).isInstanceOf(ApiResponse.class);
        ApiResponse<?> apiResponse = responseEntity.getBody();
        assertThat(apiResponse.getCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(apiResponse.getMessage()).isEqualTo("Internal server error");
        assertThat(apiResponse.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void testHandleResourceNotFoundException() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Resource", "ID", 123);

        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.handlerUserNotFoundException(ex, webRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isInstanceOf(ApiResponse.class);
        ApiResponse<?> apiResponse = responseEntity.getBody();
        assertThat(apiResponse.getCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(apiResponse.getMessage()).isEqualTo("Resource doesnt exists with: ID= '123'");
        assertThat(apiResponse.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testHandleResourceAlreadyExistsException() {
        ResourceAlreadyExistsException ex = new ResourceAlreadyExistsException("Resource", "ID", 123);

        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.handlerUserAlreadyExistsException(ex, webRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(responseEntity.getBody()).isInstanceOf(ApiResponse.class);
        ApiResponse<?> apiResponse = responseEntity.getBody();
        assertThat(apiResponse.getCode()).isEqualTo(HttpStatus.CONFLICT.value());
        assertThat(apiResponse.getMessage()).isEqualTo("Resource already exists with: ID = '123'");
        assertThat(apiResponse.getStatus()).isEqualTo(HttpStatus.CONFLICT);
    }
}
