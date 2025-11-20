package com.restaurante.backend.infrastructure.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDocumentation() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restaurante API")
                        .version("1.0.0")
                        .description("API para el sistema de pedidos para pequeños restaurantes")
                        .contact(new Contact()
                                .name("Julián Álvarez")
                                .email("contacto@restaurante.com")
                        )
                );
    }
}
