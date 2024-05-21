package com.cunoc.library.infraestructure.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResourceNotFoundExceptionTest {

    @Test
    void testResourceNotFoundExceptionMessage() {
        String resourceName = "Resource";
        String fieldName = "ID";
        Object fieldValue = 123;
        ResourceNotFoundException exception = new ResourceNotFoundException(resourceName, fieldName, fieldValue);

        String expectedMessage = String.format("%s doesnt exists with: %s= '%s'", resourceName, fieldName, fieldValue);
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void testResourceNotFoundExceptionIsRuntimeException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource", "ID", 123);

        assertThat(exception).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testResourceNotFoundExceptionHttpStatus() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource", "ID", 123);

        // Verificar que la excepción lanzada es ResourceNotFoundException
        ResourceNotFoundException thrownException = assertThrows(ResourceNotFoundException.class, () -> {
            throw exception;
        });
        assertThat(thrownException.getMessage()).isEqualTo("Resource doesnt exists with: ID= '123'");

        // Verificar que la anotación @ResponseStatus está presente y contiene el valor correcto
        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
        assertThat(responseStatus).isNotNull();
        assertThat(responseStatus.value()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testResourceNotFoundExceptionFields() {
        String resourceName = "Resource";
        String fieldName = "ID";
        Object fieldValue = 123;
        ResourceNotFoundException exception = new ResourceNotFoundException(resourceName, fieldName, fieldValue);

        assertThat(exception.getResourceName()).isEqualTo(resourceName);
        assertThat(exception.getFieldName()).isEqualTo(fieldName);
        assertThat(exception.getFieldValue()).isEqualTo(fieldValue);
    }
}
