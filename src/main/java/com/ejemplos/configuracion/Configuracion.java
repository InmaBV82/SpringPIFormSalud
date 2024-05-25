package com.ejemplos.configuracion;

//import static org.springframework.security.config.Customizer.withDefaults;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
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
	
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeRequests().requestMatchers("/**").permitAll();
//		return http.build();
//	}
//	
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	        	 registry.addMapping("/**")
	                .allowedOrigins("http://localhost:4200")
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  
	                .allowedHeaders("*");
	        }
	    };
	}
	
//	  @Bean
//	  PasswordEncoder passwordEncoder(){
//	      return new BCryptPasswordEncoder();
//	  }
	//para crear el encriptado en el spring con el Bean


	
	

}
