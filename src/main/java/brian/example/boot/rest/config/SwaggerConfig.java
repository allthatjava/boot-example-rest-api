package brian.example.boot.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Swagger UI configuration
 * 
 * @author hyen.heo
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
// extends WebMvcConfigurationSupport		// NOTE: Spring Boot doesn't required this option. If you use something else, you will need this
{
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors
				.basePackage("brian.example.boot.rest.controller"))		// Where to look for the service
//                .paths(regex("/.*") )
				.build()
				.apiInfo(metaData())		// Optional
				;
	}

	private ApiInfo metaData(){
		Contact contact = new Contact("Brian Heo", "http://{website url}", "email@whois.responsible");

		return new ApiInfo("title",
				"description",
				"version",
				"terms of Service URL",
				contact,
				"license",
				"license URL",
				new ArrayList<>());
	}

// NOTE: Spring Boot doesn't required this option. If you use something else, you will need this
//	@Override
//	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("swagger-ui.html")
//					.addResourceLocations("classpath:/META-INF/resources/");
//		registry.addResourceHandler("/webjars/**")
//					.addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}
}
