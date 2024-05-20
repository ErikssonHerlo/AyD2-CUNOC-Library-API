package com.cunoc.library.infraestructure.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResourceAlreadyExistsExceptionTest {

    @Test
    void testResourceAlreadyExistsExceptionMessage() {
        String resourceName = "Resource";
        String fieldName = "ID";
        Object fieldValue = 123;
        ResourceAlreadyExistsException exception = new ResourceAlreadyExistsException(resourceName, fieldName, fieldValue);

        String expectedMessage = String.format("%s already exists with: %s = '%s'", resourceName, fieldName, fieldValue);
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void testResourceAlreadyExistsExceptionIsRuntimeException() {
        ResourceAlreadyExistsException exception = new ResourceAlreadyExistsException("Resource", "ID", 123);

        assertThat(exception).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testResourceAlreadyExistsExceptionHttpStatus() {
        ResourceAlreadyExistsException exception = new ResourceAlreadyExistsException("Resource", "ID", 123);

        // Verificar que la excepción lanzada es ResourceAlreadyExistsException
        ResourceAlreadyExistsException thrownException = assertThrows(ResourceAlreadyExistsException.class, () -> {
            throw exception;
        });
        assertThat(thrownException.getMessage()).isEqualTo("Resource already exists with: ID = '123'");

        // Verificar que la anotación @ResponseStatus está presente y contiene el valor correcto
        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
        assertThat(responseStatus).isNotNull();
        assertThat(responseStatus.value()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testResourceAlreadyExistsExceptionFields() {
        String resourceName = "Resource";
        String fieldName = "ID";
        Object fieldValue = 123;
        ResourceAlreadyExistsException exception = new ResourceAlreadyExistsException(resourceName, fieldName, fieldValue);

        assertThat(exception.getResourceName()).isEqualTo(resourceName);
        assertThat(exception.getFieldName()).isEqualTo(fieldName);
        assertThat(exception.getFieldValue()).isEqualTo(fieldValue);
    }
}
