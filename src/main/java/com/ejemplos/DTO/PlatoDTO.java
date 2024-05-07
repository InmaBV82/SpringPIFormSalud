package com.ejemplos.DTO;


import com.ejemplos.modelo.Plato;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class PlatoDTO {
	
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
	

	public PlatoDTO(Plato plato) {
		this.id=plato.getId();
		this.nombre=plato.getNombre();
		this.autor=plato.getAutor().getNombre();
		this.categoriaNombre=plato.getCategoria().getNombre();
		this.descripcion=plato.getDescripcion();
		this.foto=plato.getFoto();
		this.ingredientes=plato.getIngredientes();
		this.tiempo=plato.getTiempo();
		

	}
	
	

}
