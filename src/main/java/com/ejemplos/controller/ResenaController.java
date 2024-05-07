package com.ejemplos.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DAO.ResenaRepositorio;
import com.ejemplos.DTO.ResenaDTO;
import com.ejemplos.modelo.Resena;

import lombok.RequiredArgsConstructor;




@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor//lombok crea el constructor
public class ResenaController {
	
	@Autowired 
	private ResenaRepositorio resenaRepositorio;

	
	@GetMapping("/resenasPlato/{platoid}")
	public ResponseEntity<?> obtenerTodos(@PathVariable int platoid) {
		List<Resena> resenasPlato = resenaRepositorio.findResenasPlato(platoid);
		
		if (resenasPlato.isEmpty()) {

		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
			
		} else {
	
			List<ResenaDTO> resenasDTO = new ArrayList<>();
		    for (Resena resena : resenasPlato) {
		    	ResenaDTO resenaDTO = new ResenaDTO(resena);
		        resenasDTO.add(resenaDTO);
		    }

			return ResponseEntity.ok(resenasDTO);
		}
	}
	
}
	