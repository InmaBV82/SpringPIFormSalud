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

import com.ejemplos.DTO.MenuDTO;
import com.ejemplos.DTO.MenuPlatoDTO;
import com.ejemplos.DTO.ResenaDTO;
import com.ejemplos.Repositorios.MenuRepositorio;
import com.ejemplos.Repositorios.MenuplatoRepositorio;
import com.ejemplos.Repositorios.PlatoRepositorio;
import com.ejemplos.modelo.Menu;
import com.ejemplos.modelo.Menuplato;
import com.ejemplos.modelo.MenuplatoPK;
import com.ejemplos.modelo.Plato;
import com.ejemplos.modelo.Resena;
import com.ejemplos.modelo.Tipo;

import lombok.RequiredArgsConstructor;




@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor//lombok crea el constructor
public class MenuplatoController {

	
	@Autowired
	private MenuplatoRepositorio menuplatoRepositorio;
	
	@Autowired
	private PlatoRepositorio platoRepositorio;

	@Autowired
	private MenuRepositorio menuRepositorio;

	
	//Todos los MenuplatoDTO
	@GetMapping("/menuPlatosDTO")
	public ResponseEntity<?> obtenerTodosDTO() {
		List<Menuplato> menuplatos = menuplatoRepositorio.findAll();

		if (menuplatos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacía");
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacía");
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
	
	
	
	//CREAR UN MENUPLATO NUEVO (tiene que existir el menu y el plato anteriormente)
	@PostMapping("/menuPlatoNuevo")
	public ResponseEntity<?> crear(@RequestBody Menuplato nuevo) {
		
	    Integer idmenu = nuevo.getId().getIdmenu();
        Integer platoid = nuevo.getId().getPlatoid();
        String momentodia = nuevo.getId().getMomentodia();

        // Verifico la existencia de Menu y Plato
        Menu menu = menuRepositorio.findById(idmenu).orElse(null);
        Plato plato = platoRepositorio.findById(platoid).orElse(null);

        if (menu == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu no encontrado");
        }

        if (plato == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plato no encontrado");
        }

        MenuplatoPK mpk = new MenuplatoPK(platoid, idmenu, momentodia);
        Menuplato menuPlato = new Menuplato();
        menuPlato.setId(mpk);
        menuPlato.setMenu(menu);
        menuPlato.setPlato(plato);

        menuplatoRepositorio.save(menuPlato);

        return ResponseEntity.status(HttpStatus.CREATED).body(menuPlato);
    
			
		
	}


    
			
		
	}
		
