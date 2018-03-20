/**
 * 
 */
package com.spc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.security.Principal;


@SpringBootApplication
@MapperScan("com.spc")
@EnableSwagger2
@EnableZuulProxy
@RestController
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	/**Swagger-ui*/
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(
					new ApiInfoBuilder()
					.title("商店管理接口").description("商店管理接口")
					.version("1.0").build()
				).select()
				.apis(RequestHandlerSelectors.basePackage("com.spc")).paths(PathSelectors.any())
				.build();
	}
}