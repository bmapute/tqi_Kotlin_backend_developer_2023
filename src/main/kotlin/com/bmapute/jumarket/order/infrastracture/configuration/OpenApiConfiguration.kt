package com.bmapute.jumarket.order.infrastracture.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.swagger.v3.core.jackson.ModelResolver
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenApiConfiguration {

    @Bean
    fun customOpenAPI():OpenAPI{
        return OpenAPI()
            .info(Info()
                .title("Shopping Cart with kotlin and Springboot ")
                .version("v1.0")
                .description("Projeto de desafio")
                .termsOfService("https://bmapute.com/desafio")
                .license(License().name("Apache 2.0")
                    .url("https://bmapute.com/desafio"))
            )

    }

    @Bean
    fun modelResolver(objectMapper: ObjectMapper): ModelResolver? {
        return ModelResolver(objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE))
    }
}