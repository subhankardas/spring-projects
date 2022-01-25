package com.codespark.springguides.swagger;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Customers API", version = "2.0", description = "Customers information API"))
public class SwaggerConfiguration {

}
