package com.ejemplos.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DAO.CategoriaRepositorio;
import com.ejemplos.DAO.TipoRepositorio;
import com.ejemplos.modelo.Categoria;
import com.ejemplos.modelo.Tipo;

import lombok.RequiredArgsConstructor;

@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor //lombok crea el constructor
public class TipoController {
	
	@Autowired 
	private TipoRepositorio tipoRepositorio;

	
	/**
	* Obtenemos todos los categorias *
	* @return 404 si no hay categorias, 200 y lista de categorias si hay uno o más
	*/
	
	@GetMapping("/tipos")
	public ResponseEntity<?> obtenerTodos() {
		List<Tipo> tipos = tipoRepositorio.findAll();
		
		if (tipos.isEmpty()) {

		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
			
		} else {

			return ResponseEntity.ok(tipos);//aqui me devuelve la lista
		}
	}
	
}
	