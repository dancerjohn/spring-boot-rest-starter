package org.example;

import org.libex.additions.rest.logging.spring.LoggingInterceptor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableSwagger2
@SpringBootApplication
@EnableAutoConfiguration(
		exclude = ErrorMvcAutoConfiguration.class) // disabled error page
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new LoggingInterceptor());
	}

//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/login").setViewName("login");
//	}

	@Bean
	public Docket getApi(){
		ApiInfo info = new ApiInfoBuilder()
				.title("My example REST App")
				.description("This is SO Cool!")
				.version("1.2")
				.build();

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(info)
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.example"))
				.paths(PathSelectors.any())
				.build();
	}
}
