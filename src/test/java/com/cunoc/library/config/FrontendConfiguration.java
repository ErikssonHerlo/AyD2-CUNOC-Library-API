package com.cunoc.library.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FrontendConfiguration {
    @Value("${frontend.url}")
    private String frontendUrl;

    @Bean
    public String frontendUrl() {
        return frontendUrl;
    }
}
