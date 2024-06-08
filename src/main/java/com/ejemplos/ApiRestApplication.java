package com.ejemplos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
		
//		ApplicationContext context = SpringApplication.run(ApiRestApplication.class, args);
		
//		// Obtén el PasswordEncoder del contexto de Spring
//		PasswordEncoder passwordEncoder = context.getBean (PasswordEncoder.class);
//		// Encripta la contraseña
//		String encodedPassword = passwordEncoder.encode("Administrador12");
//		// Muestra la contraseña encriptada 
//		System.out.println("Contraseña encriptada: "+encodedPassword);
		
	}
	
	

}
