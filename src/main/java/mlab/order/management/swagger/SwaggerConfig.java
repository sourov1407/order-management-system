package mlab.order.management.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    private static final String AUTHORIZATION_HEADER = "Authorization";

//    swagger-ui url : http://localhost:8081/api/swagger-ui.html

    @Bean
    public Docket apiDocument() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("mlab.order.management"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(metaInfo())
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()));
    }

    private ApiInfo metaInfo() {

        return new ApiInfo(
                "Order Management System",
                "Order Management System For Products",
                "1.0.0",
                "Terms of Service",
                new Contact("Sourov Sarker", "",
                        "sourov1407@gmail.com"),
                "",
                "", new ArrayList<>()
        );
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
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
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }


}
