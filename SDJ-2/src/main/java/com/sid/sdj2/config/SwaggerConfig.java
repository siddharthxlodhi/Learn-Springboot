package com.sid.sdj2.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@OpenAPIDefinition(

)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                        .title("SDJ")
                        .description("Understanding Spring Data JPA")
                        .contact(new Contact().name("SIDDHARTH")
                                .email("siddharthxlodhi@gmail.com")))
                .tags(List.of(new Tag().name("Student APIs")
                        , new Tag().name("Laptop APIs")
                        , new Tag().name("Category APIs")
                        , new Tag().name("Product APIs"))
                );
    }


}
