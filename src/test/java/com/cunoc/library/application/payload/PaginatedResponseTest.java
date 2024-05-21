package com.cunoc.library.application.payload;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class PaginatedResponseTest {

    @Test
    void testPaginatedResponseBuilder() {
        Pageable pageable = PageRequest.of(1, 10);
        PaginatedResponse<String> response = PaginatedResponse.<String>builder()
                .httpCode(200)
                .message("Success")
                .status(HttpStatus.OK)
                .data("Sample data")
                .pageable(pageable)
                .build();

        assertThat(response.getHttpCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getData()).isEqualTo("Sample data");
        assertThat(response.getPageable()).isEqualTo(pageable);
    }

    @Test
    void testPaginatedResponseConstructor() {
        Pageable pageable = PageRequest.of(1, 10);
        PaginatedResponse<String> response = new PaginatedResponse<>(200, "Success", HttpStatus.OK, "Sample data", pageable);

        assertThat(response.getHttpCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getData()).isEqualTo("Sample data");
        assertThat(response.getPageable()).isEqualTo(pageable);
    }

    @Test
    void testPaginatedResponseNoArgsConstructor() {
        PaginatedResponse<String> response = new PaginatedResponse<>();

        assertThat(response.getHttpCode()).isNull();
        assertThat(response.getMessage()).isNull();
        assertThat(response.getStatus()).isNull();
        assertThat(response.getData()).isNull();
        assertThat(response.getPageable()).isNull();

        response.setHttpCode(200);
        response.setMessage("Success");
        response.setStatus(HttpStatus.OK);
        response.setData("Sample data");
        Pageable pageable = PageRequest.of(1, 10);
        response.setPageable(pageable);

        assertThat(response.getHttpCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getData()).isEqualTo("Sample data");
        assertThat(response.getPageable()).isEqualTo(pageable);
    }

    @Test
    void testPaginatedResponseSettersAndGetters() {
        PaginatedResponse<String> response = new PaginatedResponse<>();

        response.setHttpCode(200);
        response.setMessage("Success");
        response.setStatus(HttpStatus.OK);
        response.setData("Sample data");
        Pageable pageable = PageRequest.of(1, 10);
        response.setPageable(pageable);

        assertThat(response.getHttpCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getData()).isEqualTo("Sample data");
        assertThat(response.getPageable()).isEqualTo(pageable);
    }




    @Test
    void testPaginatedResponseToString() {
        Pageable pageable = PageRequest.of(1, 10);
        PaginatedResponse<String> response = new PaginatedResponse<>(200, "Success", HttpStatus.OK, "Sample data", pageable);
        assertThat(response.toString()).contains("200", "Success", "OK", "Sample data", pageable.toString());
    }



    @Test
    void testPaginatedResponseBuilderToString() {
        Pageable pageable = PageRequest.of(1, 10);
        PaginatedResponse.PaginatedResponseBuilder<Object> builder = PaginatedResponse.builder()
                .httpCode(200)
                .message("Success")
                .status(HttpStatus.OK)
                .data("Sample data")
                .pageable(pageable);

        String builderString = builder.toString();
        assertThat(builderString).contains("PaginatedResponse.PaginatedResponseBuilder");
        assertThat(builderString).contains("httpCode=200");
        assertThat(builderString).contains("message=Success");
        assertThat(builderString).contains("status=200 OK");
        assertThat(builderString).contains("data=Sample data");
        // Adjust the expected output based on the actual string representation of pageable
        assertThat(builderString).contains("pageable=" + pageable.toString());
    }


}
