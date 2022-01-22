package com.example.warehouseservice.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration class for enabling service docs
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.warehouseservice"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {http://localhost:8080/swagger-ui.html#/
        return new ApiInfoBuilder().title("Warehouse")
                .description("Service express storing and fetching the products")
                .license("Copyright (c) test")
                .version("1.0.0")
                .build();
    }
}
