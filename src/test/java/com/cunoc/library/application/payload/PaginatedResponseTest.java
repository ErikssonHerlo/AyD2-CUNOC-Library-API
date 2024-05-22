package com.cunoc.library.application.payload;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class PaginatedResponseTest {

    @Test
    void testPaginatedResponseConstructor() {
        Pageable pageable = PageRequest.of(0, 10);
        PaginatedResponse<String> response = new PaginatedResponse<>(
                200,
                "Success",
                HttpStatus.OK,
                "Sample data",
                pageable,
                true,
                false,
                true,
                false,
                5,
                50
        );

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getData()).isEqualTo("Sample data");
        assertThat(response.getPageable()).isEqualTo(pageable);
        assertThat(response.isLast()).isTrue();
        assertThat(response.isFirst()).isFalse();
        assertThat(response.isHasNext()).isTrue();
        assertThat(response.isHasPrevious()).isFalse();
        assertThat(response.getTotalPages()).isEqualTo(5);
        assertThat(response.getTotalElements()).isEqualTo(50);
    }

    @Test
    void testPaginatedResponseSetters() {
        Pageable pageable = PageRequest.of(0, 10);
        PaginatedResponse<String> response = new PaginatedResponse<>();

        response.setCode(200);
        response.setMessage("Success");
        response.setStatus(HttpStatus.OK);
        response.setData("Sample data");
        response.setPageable(pageable);
        response.setLast(true);
        response.setFirst(false);
        response.setHasNext(true);
        response.setHasPrevious(false);
        response.setTotalPages(5);
        response.setTotalElements(50);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getData()).isEqualTo("Sample data");
        assertThat(response.getPageable()).isEqualTo(pageable);
        assertThat(response.isLast()).isTrue();
        assertThat(response.isFirst()).isFalse();
        assertThat(response.isHasNext()).isTrue();
        assertThat(response.isHasPrevious()).isFalse();
        assertThat(response.getTotalPages()).isEqualTo(5);
        assertThat(response.getTotalElements()).isEqualTo(50);
    }

    @Test
    void testPaginatedResponseBuilder() {
        Pageable pageable = PageRequest.of(0, 10);
        PaginatedResponse<String> response = PaginatedResponse.<String>builder()
                .code(200)
                .message("Success")
                .status(HttpStatus.OK)
                .data("Sample data")
                .pageable(pageable)
                .isLast(true)
                .isFirst(false)
                .hasNext(true)
                .hasPrevious(false)
                .totalPages(5)
                .totalElements(50)
                .build();

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getData()).isEqualTo("Sample data");
        assertThat(response.getPageable()).isEqualTo(pageable);
        assertThat(response.isLast()).isTrue();
        assertThat(response.isFirst()).isFalse();
        assertThat(response.isHasNext()).isTrue();
        assertThat(response.isHasPrevious()).isFalse();
        assertThat(response.getTotalPages()).isEqualTo(5);
        assertThat(response.getTotalElements()).isEqualTo(50);
    }

    @Test
    void testPaginatedResponseBuilderToString() {
        Pageable pageable = PageRequest.of(0, 10);
        PaginatedResponse.PaginatedResponseBuilder<String> builder = PaginatedResponse.<String>builder()
                .code(200)
                .message("Success")
                .status(HttpStatus.OK)
                .data("Sample data")
                .pageable(pageable)
                .isLast(true)
                .isFirst(false)
                .hasNext(true)
                .hasPrevious(false)
                .totalPages(5)
                .totalElements(50);

        String expectedString = "PaginatedResponse.PaginatedResponseBuilder(code=200, message=Success, status=200 OK, data=Sample data, pageable=" + pageable.toString() + ", isLast=true, isFirst=false, hasNext=true, hasPrevious=false, totalPages=5, totalElements=50)";
        assertThat(builder.toString()).isEqualTo(expectedString);
    }

    @Test
    void testPaginatedResponseToString() {
        Pageable pageable = PageRequest.of(0, 10);
        PaginatedResponse<String> response = PaginatedResponse.<String>builder()
                .code(200)
                .message("Success")
                .status(HttpStatus.OK)
                .data("Sample data")
                .pageable(pageable)
                .isLast(true)
                .isFirst(false)
                .hasNext(true)
                .hasPrevious(false)
                .totalPages(5)
                .totalElements(50)
                .build();

        String expectedString = "PaginatedResponse(code=200, message=Success, status=200 OK, data=Sample data, pageable=" + pageable.toString() + ", isLast=true, isFirst=false, hasNext=true, hasPrevious=false, totalPages=5, totalElements=50)";
        assertThat(response.toString()).isEqualTo(expectedString);
    }
}
