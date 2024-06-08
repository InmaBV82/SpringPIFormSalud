package com.ejemplos.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.Repositorios.CategoriaRepositorio;
import com.ejemplos.modelo.Categoria;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CategoriaController {
	
	@Autowired 
	private CategoriaRepositorio categoriaRepositorio;

	
	
	@GetMapping("/categorias")
	public ResponseEntity<?> obtenerTodos() {
		List<Categoria> categorias = categoriaRepositorio.findAllOrderedById();
		
		if (categorias.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacía");
			
		} else {

			return ResponseEntity.ok(categorias);//aqui me devuelve la lista
		}
	}
	
}
	