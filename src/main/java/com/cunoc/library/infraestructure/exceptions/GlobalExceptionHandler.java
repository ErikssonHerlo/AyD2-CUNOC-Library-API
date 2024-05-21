package com.cunoc.library.infraestructure.exceptions;

import com.cunoc.library.application.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                        WebRequest webRequest){

        Map<String, String> mapErrors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String key = ((FieldError) error).getField();
            String value = error.getDefaultMessage();
            mapErrors.put(key, value);
        });
        ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(),mapErrors.toString(), HttpStatus.BAD_REQUEST,null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> handlerInternalErrorException(BadRequestException exception,
                                                                     WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(), HttpStatus.BAD_REQUEST,null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handlerException(Exception exception,
                                                        WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerUserNotFoundException(ResourceNotFoundException exception,
                                                                    WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage(), HttpStatus.NOT_FOUND,null);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handlerUserAlreadyExistsException(ResourceAlreadyExistsException exception,
                                                                         WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(HttpStatus.CONFLICT.value(),exception.getMessage(), HttpStatus.CONFLICT,null);
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }
}
