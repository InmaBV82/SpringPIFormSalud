package com.ejemplos.DTO;


import java.util.ArrayList;
import java.util.List;

import com.ejemplos.modelo.Plato;
import com.ejemplos.modelo.Resena;

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
	
	@Getter
	@Setter
	private List<ResenaDTO> resenas;
	

	public PlatoDTO(Plato plato) {
		this.id=plato.getId();
		this.nombre=plato.getNombre();
		this.autor=plato.getAutor().getNombre();
		this.categoriaNombre=plato.getCategoria().getNombre();
		this.descripcion=plato.getDescripcion();
		this.foto=plato.getFoto();
		this.ingredientes=plato.getIngredientes();
		this.tiempo=plato.getTiempo();
		this.resenas=convertListResenasToListResenasDTO(plato.getResenas());
		

	}
	
	public List<ResenaDTO> convertListResenasToListResenasDTO(List<Resena> resenas) {
		
		List<ResenaDTO> resenasDTO = new ArrayList<>();
	    for (Resena resena : resenas) {
	    	ResenaDTO resenaDTO = new ResenaDTO(resena);
	        resenasDTO.add(resenaDTO);
	    }
		
		return resenasDTO;
	}
	

}
