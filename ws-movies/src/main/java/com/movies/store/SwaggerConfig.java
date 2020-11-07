package com.movies.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Movies Store APIs")
                .description("")
                .termsOfServiceUrl("").version("").contact(new Contact("", "", "")).build();
    }

    @Value("${gateway.host}")
    private String gatewayHost;
    
    @Value("${spring.application.name}")
    private String appName;
    
    
    @Bean
    public Docket configureControllerPackageAndConvertors() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.host(gatewayHost+"/"+appName)
        		.select()
                .apis(RequestHandlerSelectors.basePackage("com.movies.store.controller")).build()
                .apiInfo(apiInfo());
    }


}