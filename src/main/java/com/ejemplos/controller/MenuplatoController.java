package com.ejemplos.controller;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DTO.MenuPlatoDTO;
import com.ejemplos.Repositorios.MenuplatoRepositorio;
import com.ejemplos.modelo.Menuplato;
import lombok.RequiredArgsConstructor;




@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor//lombok crea el constructor
public class MenuplatoController {

	
	@Autowired
	private MenuplatoRepositorio menuplatoRepositorio;

	
	//Todos los MenuplatoDTO
		@GetMapping("/menuPlatosDTO")
		public ResponseEntity<?> obtenerTodosDTO() {
			List<Menuplato> menuplatos = menuplatoRepositorio.findAll();
			
			if (menuplatos.isEmpty()) {
			//devolvemos una respuesta como instancia de ResposeEntity
				return ResponseEntity.notFound().build();
			}else {
				List<MenuPlatoDTO> menuplatosDTO = new ArrayList<>();
			    for (Menuplato menuplato : menuplatos) {
			    	MenuPlatoDTO menuPlatoDTO = new MenuPlatoDTO(menuplato);
			    	menuplatosDTO.add(menuPlatoDTO);
			            
			       }
			    return ResponseEntity.ok(menuplatosDTO);
			}
			
		}
	
	



	
}
	