package com.ejemplos.controller;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DTO.UsuarioDTO;
import com.ejemplos.Repositorios.EmailService;
import com.ejemplos.Repositorios.HistoricoRepositorio;
import com.ejemplos.Repositorios.PlatoRepositorio;
import com.ejemplos.Repositorios.ResenaRepositorio;
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
	
	@Autowired 
	private ResenaRepositorio resenaRepositorio;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;

	
	//LISTAR TODOS LOS USUARIOS
	@GetMapping("/usuarios")
	public ResponseEntity<?> obtenerTodos() {
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		
		if (usuarios.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacía");
			
		} else {

			return ResponseEntity.ok(usuarios);
		}
	}
	
	//MOSTRAR UN USUARIO SEGUN SU ID
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> obtenerUno(@PathVariable int id) {

		Usuario result=usuarioRepositorio.findById(id).orElse(null);
		
		if(result==null)
			throw new UsuarioNotFoundException(id);
		else
			return ResponseEntity.ok(result);
	
	
}
	//LOGIN
	@PostMapping("/login")
	public ResponseEntity<?> autenticacion(@RequestBody UsuarioDTO usuLogin) {
		
		Usuario usuario=usuarioRepositorio.findByEmail(usuLogin.getEmail());
		
		if (usuario == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no registrado");
	    }

	    if (passwordEncoder.matches(usuLogin.getPassword(), usuario.getPassword())) {
	        return ResponseEntity.ok(usuario);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
	    
	    }
	    
	   }
	
	    
	
	//CREAR UN USUARIO NUEVO
	@PostMapping("/usuarioNuevo")
	public ResponseEntity<?> registro(@RequestBody Usuario nuevo) {
		
		 if (nuevo.getRol() == null || nuevo.getRol().isEmpty()) {
			 nuevo.setRol("usuario");  
		    }
		 
		// Encriptar la contraseña del usuario nuevo
		String encodedPassword = passwordEncoder.encode(nuevo.getPassword());
		nuevo.setPassword(encodedPassword);
		usuarioRepositorio.save(nuevo);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
			
		
	}
	
	
	//MODIFICAR UN USUARIO DETERMINADO
	@PutMapping("/editarUsuario/{id}")
	public ResponseEntity<?> editar(@PathVariable Integer id, @RequestBody Usuario editar) {
		
		 // Verifica si el usuario existe sino salta excepcion
	    Usuario usuarioExistente = usuarioRepositorio.findById(id)
	            .orElseThrow(() -> new UsuarioNotFoundException(id));

	    // Actualiza solo los campos necesarios
	    usuarioExistente.setNombre(editar.getNombre());
	    usuarioExistente.setEmail(editar.getEmail());
	    usuarioExistente.setPassword(editar.getPassword());
	    String encodedPassword = passwordEncoder.encode(usuarioExistente.getPassword());
	    usuarioExistente.setPassword(encodedPassword);
	    
	    if (id != 1) {
	    	 usuarioExistente.setRol("usuario");
		    }
	    else {
	    	 usuarioExistente.setRol("admin");
	    }
	   

	    // Guardo el usuario actualizado
	    Usuario usuarioActualizado = usuarioRepositorio.save(usuarioExistente);

	    return ResponseEntity.ok(usuarioActualizado);
		
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
			//eliminamos sus resenas
			resenaRepositorio.deleteByAutorId(id);
			
			usuarioRepositorio.deleteById(id);
			return ResponseEntity.noContent().build();
		}
				
	}
	
	

	//RECUPERAR CONTRASEÑA
	@PostMapping("/recuperarPassword/{nombre}/{email}")
	public ResponseEntity<?> recuperarPassword(@PathVariable String nombre, @PathVariable String email) {
		
		Usuario usu=usuarioRepositorio.findByNombreAndEmail(nombre, email);
	
		if(usu != null) {
		
			 String nuevaPassword=UUID.randomUUID().toString().substring(0, 15);
			 String encodedPassword = passwordEncoder.encode(nuevaPassword);
			 
			 usu.setPassword(encodedPassword);
			 usuarioRepositorio.save(usu);
			 emailService.enviarEmail(email, "Recuperación de la Contraseña", "La nueva contraseña es: "+ nuevaPassword);
			 
			 return ResponseEntity.ok().build();
			 
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
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
	