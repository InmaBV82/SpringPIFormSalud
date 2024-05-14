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

import com.ejemplos.DTO.HistoricoDTO;
import com.ejemplos.DTO.PlatoDTO;
import com.ejemplos.DTO.ResenaAddDTO;
import com.ejemplos.DTO.ResenaDTO;
import com.ejemplos.Repositorios.HistoricoRepositorio;
import com.ejemplos.Repositorios.PlatoRepositorio;
import com.ejemplos.Repositorios.ResenaRepositorio;
import com.ejemplos.Repositorios.UsuarioRepositorio;
import com.ejemplos.modelo.Historico;
import com.ejemplos.modelo.Plato;
import com.ejemplos.modelo.Resena;
import com.ejemplos.modelo.Usuario;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;




@RestController//indica q estamos implementando un servicio web
@RequiredArgsConstructor//lombok crea el constructor
public class HistoricoController {
	
	@Autowired 
	private HistoricoRepositorio historicoRepositorio;
	
	@Autowired
	private PlatoRepositorio platoRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuRepositorio;

	
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
	
	//Todos los historicosDTO
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
	



	
}
	