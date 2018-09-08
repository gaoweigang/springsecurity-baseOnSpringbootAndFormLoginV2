package com.gwg.user.web.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SpringSecurity使用基于表单的登录无法使用Swagger2来进行测试，因为Swagger2页面没有登录入口
 */
@Configuration
@EnableSwagger2 //启用Swagger2
public class Swagger2Config {



	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("com.gwg.user.web.controller"))//
				.build()//
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()//
				.title("shiro 系统 API")//
				.description("")//描述
				.license("")//
				.licenseUrl("http://unlicense.org")//
				.termsOfServiceUrl("")//
				.version("0.0.1")//版本号
				.contact(new Contact("", "", ""))//创建人
				.build();
	}

}
