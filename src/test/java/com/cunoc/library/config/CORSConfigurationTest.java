package com.cunoc.library.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;gst

import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CORSConfigurationTest {

    private MockMvc mockMvc;
    private CORSConfiguration corsConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        corsConfiguration = new CORSConfiguration();
        corsConfiguration.frontendUrl = "http://localhost:5173"; // Establece la URL de frontend para la prueba
        mockMvc = MockMvcBuilders.standaloneSetup(new Object())
                .addFilters(new CorsFilter(corsConfigurationSource()))
                .build();
    }

    @Test
    void testCorsConfiguration() throws Exception {
        mockMvc.perform(options("/some-endpoint")
                        .header("Access-Control-Request-Method", "GET")
                        .header("Origin", "http://localhost:5173"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:5173"))
                .andExpect(header().string("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE"))
                .andExpect(header().string("Access-Control-Allow-Credentials", "true"));
    }

    @Test
    void testCorsConfigurationInvalidOrigin() throws Exception {
        mockMvc.perform(options("/some-endpoint")
                        .header("Access-Control-Request-Method", "GET")
                        .header("Origin", "http://invalid-origin.com"))
                .andExpect(status().isForbidden()); // Cambiado para verificar que la solicitud es prohibida
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true); // Si necesitas permitir cookies y credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



}
