package com.cunoc.library.application.payload;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class PaginatedResponse<T> {
    private Integer httpCode;
    private String message;
    private HttpStatus status;
    private T data;
    private Pageable pageable;

    public PaginatedResponse(Integer httpCode, String message, HttpStatus status, T data, Pageable pageable) {
        this.httpCode = httpCode;
        this.message = message;
        this.status = status;
        this.data = data;
        this.pageable = pageable;
    }
}

