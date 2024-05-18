package com.ejemplos.controller;


import java.util.ArrayList;
import java.util.List;

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
import com.ejemplos.DTO.ResenaDTO;
import com.ejemplos.Repositorios.PlatoRepositorio;
import com.ejemplos.modelo.Plato;
import com.ejemplos.modelo.Resena;

import lombok.RequiredArgsConstructor;

@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor //lombok crea el constructor
public class PlatoController {
	
	@Autowired 
	private PlatoRepositorio platoRepositorio;
	
	
	//Todos los platos
	@GetMapping("/platos")
	public ResponseEntity<?> obtenerTodos() {
		List<Plato> platos = platoRepositorio.findAll();
		
		if (platos.isEmpty()) {
		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
			
		} else {
			
			return ResponseEntity.ok(platos);//aqui me devuelve la lista
		}
	}
	
	//Todos los platosDTO
		@GetMapping("/platosDto")
		public ResponseEntity<?> obtenerTodosDTO() {
			List<Plato> platos = platoRepositorio.findAll();
			if (platos.isEmpty()) {
//				//devolvemos una respuesta como instancia de ResposeEntity
					return ResponseEntity.notFound().build();
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
			//devolvemos una respuesta como instancia de ResposeEntity
				return ResponseEntity.notFound().build();
				
			} else {
				
				List<PlatoDTO> platosDTO = new ArrayList<>();
			    for (Plato plato : platosCategoria) {
			    	PlatoDTO platoDTO = new PlatoDTO(plato);
			        platosDTO.add(platoDTO);
			    }
			    return ResponseEntity.ok(platosDTO);
			}
		}
		
//	@GetMapping("/platosCategoria/{categoriaid}")
//	public ResponseEntity<?> platosDeCategoria(@PathVariable int categoriaid) {
//		List<Plato> platosCategoria = platoRepositorio.findPlatosDeCategoria(categoriaid);
//		
//		if (platosCategoria.isEmpty()) {
//		//devolvemos una respuesta como instancia de ResposeEntity
//			return ResponseEntity.notFound().build();
//			
//		} else {
//			
//			List<PlatoDTO> platosDTO = new ArrayList<>();
//		    for (Plato plato : platosCategoria) {
//		        PlatoDTO platoDTO = new PlatoDTO();
//		        platoDTO.setId(plato.getId());
//		        platoDTO.setNombre(plato.getNombre());
//		        platoDTO.setDescripcion(plato.getDescripcion());
//		        platoDTO.setFoto(plato.getFoto());
//		        platoDTO.setIngredientes(plato.getIngredientes());
//		        platoDTO.setTiempo(plato.getTiempo());
//		        
//		        if (plato.getCategoria() != null) {
//		            platoDTO.setCategoriaNombre(plato.getCategoria().getNombre());
//		        }
//		        
//		        if (plato.getAutor() != null) {
//		            platoDTO.setAutor(plato.getAutor().getNombre());
//		        }
//		     
//		        if (!plato.getReseñas().isEmpty()) {
//		        	List<String> listaComentarios=new ArrayList<>();
//		        	List<Integer> puntuaciones=new ArrayList<>();
//		        	for (int i = 0; i < plato.getReseñas().size(); i++) {
//		        		listaComentarios.add(plato.getReseñas().get(i).getComentario());
//		        		puntuaciones.add(plato.getReseñas().get(i).getPuntuacion());
//
//					}
//		        	platoDTO.setComentarios(listaComentarios);
//		        	platoDTO.setPuntuacion(puntuaciones);
//		            
//		        }
//		        platosDTO.add(platoDTO);
//		    }
//		    return ResponseEntity.ok(platosDTO);
//		}
//	}
	
	//BUSCAR UN PLATOD POR ID Y CONVERTIRLO A DTO
	@GetMapping("/platoDTO/{id}")
	public ResponseEntity<?> obtenerUno(@PathVariable int id) {

		Plato plato=platoRepositorio.findById(id).orElse(null);
		//notFound es el 404
		if(plato==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			
			PlatoDTO platoDto=new PlatoDTO(plato);
			return ResponseEntity.ok(platoDto);
		}
			
	
	}
	
	
	//Resenas de un usuario determinada MEJORADO
	@GetMapping("/platosUsuario/{autorid}")
	public ResponseEntity<?> platosDeUsuario(@PathVariable int autorid) {
		List<Plato> platosUsu = platoRepositorio.findPlatosUsuario(autorid);
		
		if (platosUsu.isEmpty()) {
		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
			
		} else {
			
			List<PlatoDTO> platosDTO = new ArrayList<>();
		    for (Plato plato : platosUsu) {
		    	PlatoDTO platoDTO = new PlatoDTO(plato);
		    	platosDTO.add(platoDTO);
		    }
		    return ResponseEntity.ok(platosDTO);
		}
	}
	
	
	//ADD PLATO
	@PostMapping("/plato")
	public ResponseEntity<?> crear(@RequestBody Plato nuevo) {
		
		platoRepositorio.save(nuevo);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);	
		
	}
	
	
	//EDITAR UN PLATO
	@PutMapping("/plato/{id}")
	public ResponseEntity<?> editar(@RequestBody Plato editar, @PathVariable Integer id) {
		
		//si no existe me lanza la excepcion y acaba
		if(!platoRepositorio.existsById(id)) 
			return ResponseEntity.notFound().build();
		
		//si existe lo modifica
		return ResponseEntity.ok(platoRepositorio.save(editar));
			
		
	}
	
	//BORRAR UN PLATO	
	@DeleteMapping("/plato/{id}")
	public ResponseEntity<?> borrarUsuario(@PathVariable Integer id) {	
		Plato result=platoRepositorio.findById(id).orElse(null);
		//notFound es el 404
		if(result==null)
			return ResponseEntity.notFound().build();
		else {
				
			platoRepositorio.deleteById(id);
			return ResponseEntity.noContent().build();
		}
				
	}
	
}
	