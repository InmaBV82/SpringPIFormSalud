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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.ejemplos.DTO.HistoricoDTO;
import com.ejemplos.Repositorios.HistoricoRepositorio;
import com.ejemplos.Repositorios.PlatoRepositorio;
import com.ejemplos.Repositorios.UsuarioRepositorio;
import com.ejemplos.excepciones.UsuarioNotFoundException;
import com.ejemplos.modelo.Historico;
import com.ejemplos.modelo.Plato;
import com.ejemplos.modelo.Usuario;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;




@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor//lombok crea el constructor
public class HistoricoController {
	
	@Autowired 
	private HistoricoRepositorio historicoRepositorio;
	
	@Autowired 
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired 
	private PlatoRepositorio platoRepositorio;

	//TODOS LOS MODELOS HISTORICOS
	@GetMapping("/historicos")
	public ResponseEntity<?> obtenerTodos() {
		List<Historico> historicos = historicoRepositorio.findAll();
		
		if (historicos.isEmpty()) {
		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
			
		} else {
			
			return ResponseEntity.ok(historicos);//aqui me devuelve la lista
		}
	}
	
	//Todos los HISTORICOSDTO
	@GetMapping("/historicosDto")
	public ResponseEntity<?> obtenerTodosDTO() {
		List<Historico> historicos = historicoRepositorio.findAll();
		
		if (historicos.isEmpty()) {
		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
		}else {
			List<HistoricoDTO> historicosDTO = new ArrayList<>();
		    for (Historico historico : historicos) {
		    	HistoricoDTO historicoDTO = new HistoricoDTO(historico);
		    	historicosDTO.add(historicoDTO);
		            
		       }
		    return ResponseEntity.ok(historicosDTO);
		}
		
	}
	
		//hISTORICOS de un usuario determinada 
	@GetMapping("/historicosUsuario/{usuarioid}")
	public ResponseEntity<?> platosDeCategoria(@PathVariable int usuarioid) {
		List<Historico> historicosUsu = historicoRepositorio.findHistoricosUsuario(usuarioid);
		
		if (historicosUsu.isEmpty()) {
		//devolvemos una respuesta como instancia de ResposeEntity
			return ResponseEntity.notFound().build();
			
		} else {
			
			List<HistoricoDTO> historicosDTO = new ArrayList<>();
		    for (Historico historico : historicosUsu) {
		    	HistoricoDTO historicoDTO = new HistoricoDTO(historico);
		    	historicosDTO.add(historicoDTO);
		    }
		    return ResponseEntity.ok(historicosDTO);
		}
	}
	
	
	//ADD HISTORICODTO	
	@PostMapping("/addHistoricoDTO/{usuarioid}")
	@Transactional
	public ResponseEntity<?> crear(@PathVariable int usuarioid, @RequestBody HistoricoDTO nuevoDto) {
		
		Usuario usu = usuarioRepositorio.findById(usuarioid).orElse(null);
		Plato plato= platoRepositorio.findById(nuevoDto.getPlatoid()).orElse(null);
	   
		if (usu == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no ha sido encontrado");
	    }
	   

	    Historico historicoConvertido =new Historico(); 
	    historicoConvertido.setId(nuevoDto.getIdHistorico());
	    historicoConvertido.setFecha(nuevoDto.getFecha());
	    historicoConvertido.setMomentodia(nuevoDto.getMomentodia());
	    historicoConvertido.setPlato(plato);
	    historicoConvertido.setUsuario(usu);

	    historicoRepositorio.save(historicoConvertido);  

	    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDto);
	}
	
	
	//BORRAR UN HISTORICO DETERMINADO
	@DeleteMapping("/historicoDelete/{id}")
	public ResponseEntity<?> borrarUsuario(@PathVariable Integer id) {	
		Historico historico=historicoRepositorio.findById(id).orElse(null);
		//notFound es el 404
		if(historico==null)
			return ResponseEntity.notFound().build();
		else {
				
			historicoRepositorio.deleteById(id);
			return ResponseEntity.noContent().build();
		}
				
	}
	



	
}
	