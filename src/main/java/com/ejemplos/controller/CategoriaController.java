package com.ejemplos.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DAO.CategoriaRepositorio;
import com.ejemplos.modelo.Categoria;

import lombok.RequiredArgsConstructor;

@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor //lombok crea el constructor
public class CategoriaController {
	
	@Autowired 
	private CategoriaRepositorio categoriaRepositorio;

	
	/**
	* Obtenemos todos los categorias *
	* @return 404 si no hay categorias, 200 y lista de categorias si hay uno o más
	*/
	
	@GetMapping("/categorias")
	public ResponseEntity<?> obtenerTodos() {
		List<Categoria> categorias = categoriaRepositorio.findAllOrderedById();
		
		if (categorias.isEmpty()) {

		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
			
		} else {

			return ResponseEntity.ok(categorias);//aqui me devuelve la lista
		}
	}
	
}
	