package com.ejemplos.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DTO.UsuarioDTO;
import com.ejemplos.Repositorios.HistoricoRepositorio;
import com.ejemplos.Repositorios.PlatoRepositorio;
import com.ejemplos.Repositorios.UsuarioRepositorio;
import com.ejemplos.excepciones.ApiError;
import com.ejemplos.excepciones.UsuarioNotFoundException;
import com.ejemplos.modelo.Usuario;

import lombok.RequiredArgsConstructor;

@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor //lombok crea el constructor
public class UsuarioController {
	
	@Autowired 
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired 
	private PlatoRepositorio platoRepositorio;
	
	@Autowired 
	private HistoricoRepositorio historicoRepositorio;
	
//	@Autowired
//    private PasswordEncoder passwordEncoder;

	
	//LISTAR TODOS LOS USUARIOS
	@GetMapping("/usuarios")
	public ResponseEntity<?> obtenerTodos() {
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		
		if (usuarios.isEmpty()) {

		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
			
		} else {

			return ResponseEntity.ok(usuarios);//aqui me devuelve la lista
		}
	}
	
	//MOSTRAR UN USUARIO SEGUN SU ID
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> obtenerUno(@PathVariable int id) {

		Usuario result=usuarioRepositorio.findById(id).orElse(null);
		//notFound es el 404
		if(result==null)
			throw new UsuarioNotFoundException(id);
		else
			return ResponseEntity.ok(result);
	
	
}
	//LOGIN
	@PostMapping("/login")
	public ResponseEntity<?> AUTENTICACION(@RequestBody UsuarioDTO usuLogin) {
		
		Usuario usuario=usuarioRepositorio.findByEmailAndPassword(usuLogin.getEmail(), usuLogin.getPassword());

		return ResponseEntity.ok(usuario);
			
		
	}
	
	
	//CREAR UN USUARIO NUEVO
	@PostMapping("/usuarioNuevo")
	public ResponseEntity<?> registro(@RequestBody Usuario nuevo) {
		
		 if (nuevo.getRol() == null || nuevo.getRol().isEmpty()) {
			 nuevo.setRol("usuario");  
		    }
		 
		// Encriptar la contraseña del usuario
//		String encodedPassword = passwordEncoder.encode(nuevo.getPassword());
//		nuevo.setPassword(encodedPassword);
		usuarioRepositorio.save(nuevo);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
			
		
	}
	
	//MODIFICAR UN USUARIO DETERMINADO
	@PutMapping("/usuario/{id}")
	public ResponseEntity<?> editar(@RequestBody Usuario editar, @PathVariable Integer id) {
		
		//si no existe me lanza la excepcion y acaba
		if(!usuarioRepositorio.existsById(id)) 
			throw new UsuarioNotFoundException(id);
		
		//si existe lo modifica
		return ResponseEntity.ok(usuarioRepositorio.save(editar));
			
		
	}
	
	//BORRAR UN USUARIO DETERMINADO
	@DeleteMapping("/usuarioDelete/{id}")
	public ResponseEntity<?> borrarUsuario(@PathVariable Integer id) {	
		Usuario result=usuarioRepositorio.findById(id).orElse(null);
		//notFound es el 404
		if(result==null)
			throw new UsuarioNotFoundException(id);
		else {
			//eliminamos sus platos
			platoRepositorio.deleteByAutorId(id);
			//eliminamos sus historicos
			historicoRepositorio.deleteByAutorId(id);
			usuarioRepositorio.deleteById(id);
			return ResponseEntity.noContent().build();
		}
				
	}
	
//cuando se produzca un error de este tipo ejecuta este metodo y enseña un objeto de la clase ApiError que nos hemos creado con nuestros propios atributos de error
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ApiError> handleEmpleadoNoEncontrado(UsuarioNotFoundException ex){
		ApiError apiError=new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
		
	}


	
	
	
	
	
	
	
	
}
	