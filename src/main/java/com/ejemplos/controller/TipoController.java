package com.ejemplos.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.Repositorios.CategoriaRepositorio;
import com.ejemplos.Repositorios.TipoRepositorio;
import com.ejemplos.excepciones.UsuarioNotFoundException;
import com.ejemplos.modelo.Categoria;
import com.ejemplos.modelo.Tipo;
import com.ejemplos.modelo.Usuario;

import lombok.RequiredArgsConstructor;

@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor //lombok crea el constructor
public class TipoController {
	
	@Autowired 
	private TipoRepositorio tipoRepositorio;

	
	@GetMapping("/tipos")
	public ResponseEntity<?> obtenerTodos() {
		List<Tipo> tipos = tipoRepositorio.findAll();
		
		if (tipos.isEmpty()) {

		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacía");
			
		} else {

			return ResponseEntity.ok(tipos);//aqui me devuelve la lista
		}
	}
	
	//MOSTRAR UN TIPO SEGUN SU ID
	@GetMapping("/tipo/{id}")
	public ResponseEntity<?> obtenerUno(@PathVariable int id) {

		Tipo tipo=tipoRepositorio.findById(id).orElse(null);
		//notFound es el 404
		if(tipo==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(tipo);
	}
	
}
	