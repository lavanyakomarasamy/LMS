package com.newt.lms.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lavanyak
 *
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.newt.lms")
public class SwaggerConfig {
	
	@Bean
	public Docket restfulApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("lms-api").select().paths(paths()).build()
				.apiInfo(apiInfo());
	}

	private Predicate<String> paths() {
		return regex("/LMS/.*");
	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfo("Leave Management System", "Leave Management", "1.0",
				"", "Lavanya Komarasamy", "My Page", "");

	}
}