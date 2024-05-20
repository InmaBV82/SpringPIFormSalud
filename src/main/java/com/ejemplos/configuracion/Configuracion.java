package com.ejemplos.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class Configuracion {
	
	@Bean
	ModelMapper modelMapper() {
		ModelMapper model= new ModelMapper();
		model.getConfiguration().setAmbiguityIgnored(true);
		return model;
		
	} 
	
	
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	        	 registry.addMapping("/**")
	                .allowedOrigins("http://localhost:4200")
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Include OPTIONS for pre-flight requests
	                .allowedHeaders("*");
	        }
	    };
	}
	
//	  @Bean
//	  PasswordEncoder passwordEncoder(){
//	      return new BCryptPasswordEncoder();
//	  }//para crear el encriptado en el spring con el Bean


	
	

}
