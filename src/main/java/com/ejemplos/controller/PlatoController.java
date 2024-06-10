package com.ejemplos.controller;


import java.util.ArrayList;
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

import com.ejemplos.DTO.PlatoDTO;
import com.ejemplos.DTO.MenuPlatoDTO;
import com.ejemplos.DTO.PlatoAddDTO;
import com.ejemplos.DTO.PlatoDTO;
import com.ejemplos.DTO.ResenaDTO;
import com.ejemplos.Repositorios.CategoriaRepositorio;
import com.ejemplos.Repositorios.PlatoRepositorio;
import com.ejemplos.Repositorios.UsuarioRepositorio;
import com.ejemplos.modelo.Categoria;
import com.ejemplos.modelo.Menuplato;
import com.ejemplos.modelo.Plato;
import com.ejemplos.modelo.Plato;
import com.ejemplos.modelo.Resena;
import com.ejemplos.modelo.Usuario;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor //lombok crea el constructor
public class PlatoController {
	
	@Autowired 
	private PlatoRepositorio platoRepositorio;
	
	@Autowired 
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired 
	private CategoriaRepositorio categoriaRepositorio;
	
	
	//Todos los platos
	@GetMapping("/platos")
	public ResponseEntity<?> obtenerTodos() {
		List<Plato> platos = platoRepositorio.findAll();
		
		if (platos.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacía");
			
		} else {
			
			return ResponseEntity.ok(platos);
		}
	}
	
	//Todos los platosDTO
	@GetMapping("/platosDto")
	public ResponseEntity<?> obtenerTodosDTO() {
		List<Plato> platos = platoRepositorio.findAll();
		if (platos.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacía");
		}else {
			List<PlatoDTO> platosDTO = new ArrayList<>();
		    for (Plato plato : platos) {
		    	PlatoDTO platoDTO = new PlatoDTO(plato);
		    	 platosDTO.add(platoDTO);
		            
		       }
		    return ResponseEntity.ok(platosDTO);
		}
		
	}
	

	//Platos de una Categoria determinada MEJORADO
	@GetMapping("/platosCategoria/{categoriaid}")
	public ResponseEntity<?> platosDeCategoria(@PathVariable int categoriaid) {
		List<Plato> platosCategoria = platoRepositorio.findPlatosDeCategoria(categoriaid);
		
		if (platosCategoria.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacía");
			
		} else {
			
			List<PlatoDTO> platosDTO = new ArrayList<>();
		    for (Plato plato : platosCategoria) {
		    	PlatoDTO platoDTO = new PlatoDTO(plato);
		        platosDTO.add(platoDTO);
		    }
		    return ResponseEntity.ok(platosDTO);
		}
	}
	
	//BUSCAR UN PLATOD POR ID Y CONVERTIRLO A DTO
	@GetMapping("/platoDTO/{platoid}")
	public ResponseEntity<?> obtenerUnDto(@PathVariable int platoid) {

		Plato plato=platoRepositorio.findById(platoid).orElse(null);

		if(plato==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plato no encontrado");
		}
		else {
			
			PlatoDTO platoDto=new PlatoDTO(plato);
			return ResponseEntity.ok(platoDto);
		}
			
	
	}
	

	//BUSCAR UN PLATOD POR ID Y CONVERTIRLO A AddDTO(para que salga relleno el form por defecto con el plato a editar)
	@GetMapping("/platoAddDTO/{platoid}")
	public ResponseEntity<?> obtenerUnPlatoAddDto(@PathVariable int platoid) {

		Plato plato=platoRepositorio.findById(platoid).orElse(null);
		
		if(plato==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plato no encontrado");
		}
		else {
			
			PlatoAddDTO platoAddDto=new PlatoAddDTO(plato);
			return ResponseEntity.ok(platoAddDto);
		}
			
	
	}
	
	
	//Platos de un usuario determinada convertidos en DTO
	@GetMapping("/platosUsuario/{autorid}")
	public ResponseEntity<?> platosDeUsuario(@PathVariable int autorid) {
		List<Plato> platosUsu = platoRepositorio.findPlatosUsuario(autorid);
		
		if (platosUsu.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacía");
			
		} else {
			
			List<PlatoDTO> platosDTO = new ArrayList<>();
		    for (Plato plato : platosUsu) {
		    	PlatoDTO platoDTO = new PlatoDTO(plato);
		    	platosDTO.add(platoDTO);
		    }
		    return ResponseEntity.ok(platosDTO);
		}
	}
	
	//FILTRO POR NOMBRE PLATO
	@GetMapping("/filtroNombrePlato/{nombre}")
	public ResponseEntity<?> filtroNombreplatos(@PathVariable String nombre) {
		
		List<Plato> platos = platoRepositorio.filtroNombrePlato(nombre);
		
		if (platos.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacía");
			
		} else {
			
			List<PlatoDTO> platosFiltradosDTO = new ArrayList<>();
		    for (Plato plato : platos) {
		    	PlatoDTO platoDTO = new PlatoDTO(plato);
		    	platosFiltradosDTO.add(platoDTO);
		    }
		    return ResponseEntity.ok(platosFiltradosDTO);
		}
	}
	
	
	
	
	// ADD PLATO A UN USUARIO LOGUEADO
	@PostMapping("/addPlatoDTO/{usuarioid}")
	public ResponseEntity<?> crear(@PathVariable int usuarioid, @RequestBody PlatoAddDTO nuevo) {

		Usuario usu = usuarioRepositorio.findById(usuarioid).orElse(null);
		Categoria cat= categoriaRepositorio.findById(nuevo.getCategoriaid()).orElse(null);
		
		if (usu == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no ha sido encontrado");
		}

		Plato platoConvertido = new Plato();
		platoConvertido.setId(nuevo.getId());
		platoConvertido.setNombre(nuevo.getNombre());
		platoConvertido.setDescripcion(nuevo.getDescripcion());
		
		  // Asignar una foto por defecto si es nula o vacía
	    if (nuevo.getFoto() != null && !nuevo.getFoto().isEmpty()) {
	        platoConvertido.setFoto(nuevo.getFoto());
	    } else {
	        platoConvertido.setFoto("logo.png");
	    }
		
		platoConvertido.setIngredientes(nuevo.getIngredientes());
		platoConvertido.setTiempo(nuevo.getTiempo());
		platoConvertido.setCategoria(cat);
		platoConvertido.setAutor(usu);

		platoRepositorio.save(platoConvertido);

		return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

	}
	
	
	//EDITAR UN PLATOAddDTO
	@PutMapping("/editPlatoAddDTO/{idPlato}")
	@Transactional
	public ResponseEntity<?> update(@PathVariable int idPlato, @RequestBody PlatoAddDTO updatePlatoDto) {
		
		Usuario usu = usuarioRepositorio.findById(updatePlatoDto.getAutorid()).orElse(null);
		Categoria cat= categoriaRepositorio.findById(updatePlatoDto.getCategoriaid()).orElse(null);
		Plato platoConvertido =platoRepositorio.findById(idPlato).orElse(null); 
	   
		if (usu == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no ha sido encontrado");
	    }
		
		if(platoConvertido==null) {
			return ResponseEntity.notFound().build();
		}
		else {

		 
			platoConvertido.setNombre(updatePlatoDto.getNombre());
		    platoConvertido.setDescripcion(updatePlatoDto.getDescripcion());
		    platoConvertido.setIngredientes(updatePlatoDto.getIngredientes());
		    platoConvertido.setTiempo(updatePlatoDto.getTiempo());
		    platoConvertido.setFoto(updatePlatoDto.getFoto());
		    platoConvertido.setCategoria(cat);
		    platoConvertido.setAutor(usu);

		    platoRepositorio.save(platoConvertido);  

		    return ResponseEntity.status(HttpStatus.CREATED).body(updatePlatoDto);
			
		}
		
	}
	
	//BORRAR UN PLATO	
	@DeleteMapping("/platoDelete/{id}")
	public ResponseEntity<?> borrarPlato(@PathVariable Integer id) {	
		
		Plato result=platoRepositorio.findById(id).orElse(null);
	
		if(result==null)
			return ResponseEntity.notFound().build();
		else {
				
			platoRepositorio.deleteById(id);
			return ResponseEntity.noContent().build();
		}
				
	}
	
}
	