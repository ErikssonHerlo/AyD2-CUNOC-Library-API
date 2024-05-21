package com.cunoc.library.infraestructure.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadRequestExceptionTest {

    @Test
    void testBadRequestExceptionMessage() {
        String errorMessage = "This is a bad request";
        BadRequestException exception = new BadRequestException(errorMessage);

        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void testBadRequestExceptionIsRuntimeException() {
        BadRequestException exception = new BadRequestException("This is a bad request");

        assertThat(exception).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testBadRequestExceptionHttpStatus() {
        BadRequestException exception = new BadRequestException("This is a bad request");

        // Verificar que la excepción lanzada es BadRequestException
        BadRequestException thrownException = assertThrows(BadRequestException.class, () -> {
            throw exception;
        });
        assertThat(thrownException.getMessage()).isEqualTo("This is a bad request");

        // Verificar que la anotación @ResponseStatus está presente y contiene el valor correcto
        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
        assertThat(responseStatus).isNotNull();
        assertThat(responseStatus.value()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
