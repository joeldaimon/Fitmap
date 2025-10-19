package com.fitmap.Fitmap.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI fitmapOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Fitmap API")
                        .description("API para gestión de fitness")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio del proyecto")
                        .url("https://github.com/tu-usuario/fitmap"));
    }
}

