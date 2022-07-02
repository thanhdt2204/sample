package com.example.sample.config;

import com.google.common.collect.Lists;
import com.sun.istack.Nullable;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .directModelSubstitute(Pageable.class, SwaggerPageable.class)
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()));
        docket = docket.select()
                .apis(RequestHandlerSelectors.basePackage("com.example.sample.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }

    @Getter
    private static class SwaggerPageable {

        @ApiParam(name = "page", value = "Page you want to retrieve", example = "0", defaultValue = "0")
        @Nullable
        private Integer page;

        @ApiParam(name = "size", value = "Number of records per page", example = "10", defaultValue = "10")
        @Nullable
        private Integer size;

        @ApiParam(name = "sort", value = "Sorting by format: property(,asc|desc). Default sort order is ascending. " + "Multiple sort are supported")
        @Nullable
        private String[] sort;

    }

}
