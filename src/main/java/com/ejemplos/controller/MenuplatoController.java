package com.ejemplos.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DTO.MenuPlatoDTO;
import com.ejemplos.DTO.ResenaDTO;
import com.ejemplos.Repositorios.MenuplatoRepositorio;
import com.ejemplos.modelo.Menuplato;
import com.ejemplos.modelo.Resena;

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
		    
		 // Agrupar los MenuPlatoDTO por menuid() y de valor mostrar una lista con toda la info de la entidad menuPlato
		    Map<Integer, List<MenuPlatoDTO>> agruparPorMenuid = menuplatosDTO.stream()
		            .collect(Collectors.groupingBy(MenuPlatoDTO::getMenuid));

	  
		 
		  return ResponseEntity.ok(agruparPorMenuid);
		}
		
	}
		
	//filtro menuplato por el tipo de menu (filtro)
	@GetMapping("/filtroMenuPlato/{tipo}")
	public ResponseEntity<?> filtromenuplatos(@PathVariable String tipo) {
		List<Menuplato> menusplatos = menuplatoRepositorio.filtroMenuplatoPorTipo(tipo);
		
		if (menusplatos.isEmpty()) {
		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
		}else {
			List<MenuPlatoDTO> menusDto=new ArrayList<>();
			for (Menuplato menuplato: menusplatos) {
				MenuPlatoDTO menuPlatoDTO = new MenuPlatoDTO(menuplato);
				menusDto.add(menuPlatoDTO);
				
			}
			 // Agrupar los MenuPlatoDTO por menuid() y de valor mostrar toda la info  
		    Map<Integer, List<MenuPlatoDTO>> agruparPorMenuid = menusDto.stream()
		            .collect(Collectors.groupingBy(MenuPlatoDTO::getMenuid));

		    	
		    return ResponseEntity.ok(agruparPorMenuid);
		}
		
	}
	

	



	
}
	