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
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired 
	private PlatoRepositorio platoRepositorio;


	
	@GetMapping("/resenas")//(sólo para el admin)
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
		
		
	//BUSCAR UNA RESENA POR ID Y CONVERTIRLO A AddDTO(para la llamada put que salga relleno el form por defecto con la resena a editar)
	@GetMapping("/resenaAddDTO/{id}")
	public ResponseEntity<?> obtenerUnPlatoAddDto(@PathVariable int id) {

		Resena resena=resenaRepositorio.findById(id).orElse(null);
		//notFound es el 404
		if(resena==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			
			ResenaAddDTO resenaAddDto=new ResenaAddDTO(resena);
			return ResponseEntity.ok(resenaAddDto);
		}
			
	
	}	
	
		
	//Resenas de un usuario determinada
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
	
	//filtro las resenas de una puntuación introducida (filtro)
	@GetMapping("/filtroResenaPuntuacion/{puntuacion}")
	public ResponseEntity<?> puntuacionResenas(@PathVariable int puntuacion) {
		List<Resena> resenasPuntuacion = resenaRepositorio.findPuntuacionResenas(puntuacion);
		
		if (resenasPuntuacion.isEmpty()) {
		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
		}else {
			List<ResenaDTO> resenasPuntuacionDTO = new ArrayList<>();
		    for (Resena resena : resenasPuntuacion) {
		    	ResenaDTO resenaDTO = new ResenaDTO(resena);
		    	resenasPuntuacionDTO.add(resenaDTO);
		            
		       }
		    return ResponseEntity.ok(resenasPuntuacionDTO);
		}
		
	}
	
	
	//CREAR RESEÑA NUEVA
	@Transactional//asegura que la operación de base de datos es completa y se manejan adecuadamente las excepciones. Si algo falla, la transacción se revertirá.
	@PostMapping("/resenaAddDto/{usuarioid}")
	public ResponseEntity<?> crearResena(@PathVariable int usuarioid, @RequestBody ResenaAddDTO resenaAddDto) {
		
			Usuario usu = usuarioRepositorio.findById(usuarioid).orElse(null);
			Plato plato= platoRepositorio.findById(resenaAddDto.getPlatoId()).orElse(null);
		   
			if (usu == null) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no ha sido encontrado");
		    }

		    Resena nuevaResena = new Resena();
		    nuevaResena.setId(resenaAddDto.getId());
		    nuevaResena.setComentario(resenaAddDto.getComentario());
		    nuevaResena.setFecha(resenaAddDto.getFecha());
		    nuevaResena.setPuntuacion(resenaAddDto.getPuntuacion());
		    
		    nuevaResena.setUsuario(usu);
		    nuevaResena.setPlato(plato);

		    resenaRepositorio.save(nuevaResena);

		    return ResponseEntity.ok(nuevaResena);
		    
	}
	
	//EDITAR UN PLATOAddDTO
	@PutMapping("/editResenaAddDto/{idResena}")
	@Transactional
	public ResponseEntity<?> update(@PathVariable int idResena, @RequestBody ResenaAddDTO updateResenaDto) {
		
		Usuario usu = usuarioRepositorio.findById(updateResenaDto.getAutorId()).orElse(null);
		Plato plato =platoRepositorio.findById(updateResenaDto.getPlatoId()).orElse(null); 
		Resena resenaConvertida=resenaRepositorio.findById(idResena).orElse(null);
	   
		if (usu == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no ha sido encontrado");
	    }
		
		if(resenaConvertida==null) {
			return ResponseEntity.notFound().build();
		}
		
		else {

		    resenaConvertida.setComentario(updateResenaDto.getComentario());
		    resenaConvertida.setFecha(updateResenaDto.getFecha());
		    resenaConvertida.setPuntuacion(updateResenaDto.getPuntuacion());
		    
		    resenaConvertida.setUsuario(usu);
		    resenaConvertida.setPlato(plato);


		    resenaRepositorio.save(resenaConvertida);  

		    return ResponseEntity.status(HttpStatus.CREATED).body(resenaConvertida);
			
		}
		
	}
		
	//BORRAR UNA RESENA	(sólo para el admin en el front)
	@DeleteMapping("/resenaDelete/{id}")
	public ResponseEntity<?> borrarResena(@PathVariable Integer id) {	
		
		Resena result=resenaRepositorio.findById(id).orElse(null);

		if(result==null)
			
			return ResponseEntity.notFound().build();
		
		else {
		
			resenaRepositorio.deleteById(id);
			return ResponseEntity.noContent().build();
				
			
		}
				
	}
	
	



	
	
}
	