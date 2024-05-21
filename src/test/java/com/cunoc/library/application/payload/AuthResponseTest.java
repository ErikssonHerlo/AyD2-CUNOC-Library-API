package com.cunoc.library.application.payload;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthResponseTest {

    @Test
    void testAuthResponseBuilder() {
        AuthResponse authResponse = AuthResponse.builder()
                .token("sample_token")
                .build();

        assertThat(authResponse.getToken()).isEqualTo("sample_token");
    }

    @Test
    void testAuthResponseAllArgsConstructor() {
        AuthResponse authResponse = new AuthResponse("sample_token");
        assertThat(authResponse.getToken()).isEqualTo("sample_token");
    }

    @Test
    void testAuthResponseNoArgsConstructor() {
        AuthResponse authResponse = new AuthResponse();
        assertThat(authResponse.getToken()).isNull();
        authResponse.setToken("sample_token");
        assertThat(authResponse.getToken()).isEqualTo("sample_token");
    }

    @Test
    void testAuthResponseSettersAndGetters() {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken("sample_token");

        assertThat(authResponse.getToken()).isEqualTo("sample_token");
    }



    @Test
    void testAuthResponseToString() {
        AuthResponse authResponse = new AuthResponse("sample_token");
        assertThat(authResponse.toString()).contains("sample_token");
    }


    @Test
    void testAuthResponseBuilderToString() {
        AuthResponse.AuthResponseBuilder builder = AuthResponse.builder()
                .token("sample_token");

        assertThat(builder.toString()).contains("AuthResponse.AuthResponseBuilder(token=sample_token)");
    }
}
