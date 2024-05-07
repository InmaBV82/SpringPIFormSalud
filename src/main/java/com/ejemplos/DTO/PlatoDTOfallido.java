package com.ejemplos.DTO;

import java.util.List;

import com.ejemplos.modelo.Categoria;
import com.ejemplos.modelo.Resena;
import com.ejemplos.modelo.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class PlatoDTOfallido {
	
	@Getter
	@Setter
	private int id;
	
	@Getter
	@Setter
	private String nombre;

	@Getter
	@Setter
	private String descripcion;


	@Getter
	@Setter
	private String foto;

	@Getter
	@Setter
	private String ingredientes;

	@Getter
	@Setter
	private int tiempo;
	
	@Getter
	@Setter
	private String categoriaNombre;
	
	@Getter
	@Setter
	private String autor;
	
	@Getter
	@Setter
	private List<Integer> puntuacion;
	
	
	@Getter
	@Setter
	private List<String> comentarios;
	

	public PlatoDTOfallido() {
		super();

	}
	
	

}
