package com.cunoc.library.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CUNOC Library API",
                version = "1.0.0",
                description = "This is a API Documentation to the App CUNOC Library"
        )
)
public class SwaggerConfiguration {
}