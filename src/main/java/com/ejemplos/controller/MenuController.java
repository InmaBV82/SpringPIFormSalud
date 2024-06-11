package com.ejemplos.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DTO.HistoricoDTO;
import com.ejemplos.DTO.MenuDTO;
import com.ejemplos.Repositorios.MenuRepositorio;
import com.ejemplos.Repositorios.TipoRepositorio;
import com.ejemplos.modelo.Menu;
import com.ejemplos.modelo.Tipo;

import lombok.RequiredArgsConstructor;




@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor//lombok crea el constructor
public class MenuController {

	
	@Autowired
	private MenuRepositorio menuRepositorio;

	@Autowired
	private TipoRepositorio tipoRepositorio;

	
	//Todos los Menus
	@GetMapping("/menus")
	public ResponseEntity<?> obtenerTodos() {
		
		List<Menu> menus = menuRepositorio.findAll();

		if (menus.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacía");
		}else {
		
		 
		  return ResponseEntity.ok(menus);
		}
		
	}
	
	//Todos los Menus
	@GetMapping("/menusDto")
	public ResponseEntity<?> obtenerTodosDTO() {
		
		List<Menu> menus = menuRepositorio.findAll();

		if (menus.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacía");
		}else {
			List<MenuDTO> menusDTO = new ArrayList<>();
		    for (Menu menu : menus) {
		    	MenuDTO menuDTO = new MenuDTO(menu);
		    	menusDTO.add(menuDTO);
		            
		       }
		    return ResponseEntity.ok(menusDTO);
		
		}
		
	}
	
	//MOSTRAR UN Menu SEGUN SU ID
	@GetMapping("/menu/{id}")
	public ResponseEntity<?> obtenerUno(@PathVariable int id) {

		Menu result=menuRepositorio.findById(id).orElse(null);
		
		if(result==null)
			return ResponseEntity.noContent().build();
		else {
			
			return ResponseEntity.ok(result);
		}
			

	}
	
	//CREAR UN MENU NUEVO
	@PostMapping("/menuNuevo")
	public ResponseEntity<?> crear(@RequestBody MenuDTO nuevo) {
		
		Tipo tipo = tipoRepositorio.findById(nuevo.getTipoid()).orElse(null);

		Menu menuConvertido=new Menu();
		menuConvertido.setId(nuevo.getIdMenu());
		menuConvertido.setNombre(nuevo.getNombre());
		menuConvertido.setTipo(tipo);
		

		menuRepositorio.save(menuConvertido);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
			
		
	}
	
	//EDITAR UN MENU NUEVO
	@PutMapping("/menuEdit/{id}")
	public ResponseEntity<?> editar(@PathVariable int id, @RequestBody MenuDTO update) {
		
		Tipo tipo = tipoRepositorio.findById(update.getTipoid()).orElse(null);
		 
	    Menu menuActualizado = menuRepositorio.findById(id).orElse(null);
	    if(menuActualizado==null) {
	    	
			return ResponseEntity.notFound().build();
		}
		else {
			
			menuActualizado.setNombre(update.getNombre());
			menuActualizado.setTipo(tipo);
			
			menuRepositorio.save(menuActualizado);
			return ResponseEntity.ok(update);
		}
		
	}
	
	
	//BORRAR UN Menu SEGUN SU ID
	@DeleteMapping("/menuDelete/{id}")
	public ResponseEntity<?> borrar(@PathVariable Integer id) {

		Menu result=menuRepositorio.findById(id).orElse(null);
		
		if(result==null)
			
			return ResponseEntity.notFound().build();
		
		else {
			
			menuRepositorio.deleteById(id);
			return ResponseEntity.noContent().build();
			
		}
			

	}
	
	
	
	
	
 
		

	



	
}
	