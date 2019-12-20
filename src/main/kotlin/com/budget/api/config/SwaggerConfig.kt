package com.budget.api.config

import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Created by Victor Santos on 10/12/2019
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(Predicates.not(PathSelectors.regex("/error.*")))
            .build()
            .apiInfo(apiInfo())
    }

    fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Controle Orçamental API REST")
            .description("Aplicação feita com Spring Boot utilizando a linguagem Kotlin")
            .version("1.0")
            .contact(Contact("Victor Santos", "", "victor.udi10@hotmail.com"))
            .build()
    }
}