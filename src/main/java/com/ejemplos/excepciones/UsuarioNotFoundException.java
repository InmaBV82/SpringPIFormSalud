package com.ejemplos.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//al lanzar la excepcion será un codigo 404 el que reciba el cliente
//y no el 500 como seria en un principio
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UsuarioNotFoundException(long id) {
		super("No se puede encontrar el usuario con la ID: "+ id);
	}
	

	
	

}
