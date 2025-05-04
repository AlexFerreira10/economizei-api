package com.economizei.api.core;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Economizei")
                        .version("v1.0")
                        .description("Documentação interativa dos endpoints de usuário, conta, extrato e transação")
                );
    }
}