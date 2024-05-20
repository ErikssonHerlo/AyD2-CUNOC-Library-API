package com.cunoc.library.application.payload;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiResponseTest {

    @Test
    void testApiResponseBuilder() {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .code(200)
                .message("Success")
                .status(HttpStatus.OK)
                .data("Sample data")
                .build();

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getData()).isEqualTo("Sample data");
    }

    @Test
    void testApiResponseConstructor() {
        ApiResponse<String> response = new ApiResponse<>(200, "Success", HttpStatus.OK, "Sample data");

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getData()).isEqualTo("Sample data");
    }

    @Test
    void testApiResponseNoArgsConstructor() {
        ApiResponse<String> response = new ApiResponse<>();

        assertThat(response.getCode()).isNull();
        assertThat(response.getMessage()).isNull();
        assertThat(response.getStatus()).isNull();
        assertThat(response.getData()).isNull();

        response.setCode(200);
        response.setMessage("Success");
        response.setStatus(HttpStatus.OK);
        response.setData("Sample data");

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getData()).isEqualTo("Sample data");
    }

    @Test
    void testApiResponseSettersAndGetters() {
        ApiResponse<String> response = new ApiResponse<>();

        response.setCode(200);
        response.setMessage("Success");
        response.setStatus(HttpStatus.OK);
        response.setData("Sample data");

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getData()).isEqualTo("Sample data");
    }


    @Test
    void testApiResponseToString() {
        ApiResponse<String> response = new ApiResponse<>(200, "Success", HttpStatus.OK, "Sample data");
        assertThat(response.toString()).contains("200", "Success", "OK", "Sample data");
    }


    @Test
    void testApiResponseBuilderToString() {
        ApiResponse.ApiResponseBuilder<Object> builder = ApiResponse.builder()
                .code(200)
                .message("Success")
                .status(HttpStatus.OK)
                .data("Sample data");

        assertThat(builder.toString()).contains("ApiResponse.ApiResponseBuilder(code=200, message=Success, status=200 OK, data=Sample data)");

    }
}