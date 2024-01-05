package com.coleccionvideojuegos.springboot.web.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	 @Override
	  public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/api/**")
	      .allowedOrigins("https://www.metacritic.com")
	      .allowedMethods("GET", "POST")
	      .allowedHeaders("header1", "header2", "header3")
	      .exposedHeaders("header1", "header2")
	      .allowCredentials(true).maxAge(3600);
	  }
	
	
	
	@Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:8080");
            }
        };
    }
}
