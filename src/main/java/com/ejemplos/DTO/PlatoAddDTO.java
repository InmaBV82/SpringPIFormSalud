package com.ejemplos.DTO;

import java.util.List;

import com.ejemplos.modelo.Categoria;
import com.ejemplos.modelo.Plato;
import com.ejemplos.modelo.Resena;
import com.ejemplos.modelo.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class PlatoAddDTO {
	
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
	private int categoriaid;
	
	@Getter
	@Setter
	private String categoriaNombre;
	
	@Getter
	@Setter
	private int autorid;
	
	
	public PlatoAddDTO(Plato plato) {
		this.id=plato.getId();
		this.nombre=plato.getNombre();
		this.descripcion=plato.getDescripcion();
		this.foto=plato.getFoto();
		this.ingredientes=plato.getIngredientes();
		this.tiempo=plato.getTiempo();
		this.categoriaid=plato.getCategoria().getId();
		this.categoriaNombre=plato.getCategoria().getNombre();
		this.autorid=plato.getAutor().getId();

	}
	
	

}
