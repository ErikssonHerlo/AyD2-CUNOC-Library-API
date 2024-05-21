package com.cunoc.library.application.payload;

import lombok.*;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    String token;
}