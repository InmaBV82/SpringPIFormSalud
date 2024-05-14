package com.ejemplos.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ejemplos.DTO.PlatoDTO;
import com.ejemplos.DTO.ResenaAddDTO;
import com.ejemplos.DTO.ResenaDTO;
import com.ejemplos.Repositorios.PlatoRepositorio;
import com.ejemplos.Repositorios.ResenaRepositorio;
import com.ejemplos.Repositorios.UsuarioRepositorio;
import com.ejemplos.modelo.Plato;
import com.ejemplos.modelo.Resena;
import com.ejemplos.modelo.Usuario;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;




@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor//lombok crea el constructor
public class ResenaController {
	
	@Autowired 
	private ResenaRepositorio resenaRepositorio;
	
	@Autowired
	private PlatoRepositorio platoRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuRepositorio;

	
	@GetMapping("/resenas")
	public ResponseEntity<?> obtenerTodos() {
		List<Resena> resenas = resenaRepositorio.findAll();
		
		if (resenas.isEmpty()) {
		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
			
		} else {
			
			return ResponseEntity.ok(resenas);//aqui me devuelve la lista
		}
	}
	
	//Todos las resenasDTO
		@GetMapping("/resenasDto")
		public ResponseEntity<?> obtenerTodosDTO() {
			List<Resena> resenas = resenaRepositorio.findAll();
			
			if (resenas.isEmpty()) {
			//devolvemos una respuesta como instancia de ResposeEntity
				return ResponseEntity.notFound().build();
			}else {
				List<ResenaDTO> resenasDTO = new ArrayList<>();
			    for (Resena resena : resenas) {
			    	ResenaDTO resenaDTO = new ResenaDTO(resena);
			    	resenasDTO.add(resenaDTO);
			            
			       }
			    return ResponseEntity.ok(resenasDTO);
			}
			
		}
		
//Resenas de un usuario determinada MEJORADO
	@GetMapping("/resenasUsuario/{usuarioid}")
	public ResponseEntity<?> platosDeCategoria(@PathVariable int usuarioid) {
		List<Resena> resenasUsu = resenaRepositorio.findResenasUsuario(usuarioid);
		
		if (resenasUsu.isEmpty()) {
		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
			
		} else {
			
			List<ResenaDTO> resenasDTO = new ArrayList<>();
		    for (Resena resena : resenasUsu) {
		    	ResenaDTO resenaDTO = new ResenaDTO(resena);
		    	resenasDTO.add(resenaDTO);
		    }
		    return ResponseEntity.ok(resenasDTO);
		}
	}
	
	//MOSTRAR LAS RESENAS DE UN PLATO
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
	
	
	//CREAR RESEÑA NUEVA
	@Transactional//asegura que la operación de base de datos es completa y se manejan adecuadamente las excepciones. Si algo falla, la transacción se revertirá.
	@PostMapping("/resenaNueva")
	public ResponseEntity<?> crearResena(@RequestBody ResenaAddDTO resenaAddDto) {
		
			Usuario usuario = usuRepositorio.findById(resenaAddDto.getAutorId())
		        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
		    
			Plato plato = platoRepositorio.findById(resenaAddDto.getPlatoId())
		        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plato no encontrado"));

		    Resena nuevaResena = new Resena();
		    nuevaResena.setComentario(resenaAddDto.getComentario());
		    nuevaResena.setPuntuacion(resenaAddDto.getPuntuacion());
		    nuevaResena.setFecha(resenaAddDto.getFecha());  // Considera usar un formato de fecha apropiado
		    nuevaResena.setUsuario(usuario);
		    nuevaResena.setPlato(plato);

		    resenaRepositorio.save(nuevaResena);

		    return ResponseEntity.ok(nuevaResena);
		    
	}

	
}
	