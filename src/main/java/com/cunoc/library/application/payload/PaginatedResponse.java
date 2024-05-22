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
    private Integer code;
    private String message;
    private HttpStatus status;
    private T data;
    private Pageable pageable;

    public PaginatedResponse(Integer code, String message, HttpStatus status, T data, Pageable pageable) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
        this.pageable = pageable;
    }
}

