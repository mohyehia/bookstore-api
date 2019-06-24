package com.mohyehia.bookstore.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mohyehia.bookstore"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Bookstore Api Documentation",
				"Bookstore service documentation for the web api",
				"1.0",
				"Terms of Service",
				new Contact("Mohammed Yehia", "https://linkedin.com/in/moh-yehia", "mohammedyehia99@gmail.com"),
				"Moh-yehia licence",
				"https://www.google.com",
				Collections.emptyList()
		);
	}
}
