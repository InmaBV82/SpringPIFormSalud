package com.ejemplos.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DAO.ReseñaRepositorio;
import com.ejemplos.DTO.PlatoDTOfallido;
import com.ejemplos.DTO.ReseñaDTO;
import com.ejemplos.modelo.Plato;
import com.ejemplos.modelo.Reseña;

import lombok.RequiredArgsConstructor;




@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor//lombok crea el constructor
public class ReseñaController {
	
	@Autowired 
	private ReseñaRepositorio reseñaRepositorio;

	
	@GetMapping("/resenasPlato/{platoid}")
	public ResponseEntity<?> obtenerTodos(@PathVariable int platoid) {
		List<Reseña> reseñasPlato = reseñaRepositorio.findReseñasPlato(platoid);
		
		if (reseñasPlato.isEmpty()) {

		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
			
		} else {
	
			List<ReseñaDTO> reseñasDTO = new ArrayList<>();
		    for (Reseña reseña : reseñasPlato) {
		    	ReseñaDTO reseñaDTO = new ReseñaDTO(reseña);
		        reseñasDTO.add(reseñaDTO);
		    }

			return ResponseEntity.ok(reseñasDTO);
		}
	}
	
}
	