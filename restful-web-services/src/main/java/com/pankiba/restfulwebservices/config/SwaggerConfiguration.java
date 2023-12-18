package com.pankiba.restfulwebservices.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/json"));
	// @formatter:off

	@Bean
	public Docket restApiDocumentation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("RESTful API Documentaion")
				.description("RESTful API Documentaion")
				.contact(new Contact("pankiba", "https://github.com/pankiba", "pankiba@users.noreply.github.com"))
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.version("1.0.0")
				.build();
	}
	
	// @formatter:on
}
