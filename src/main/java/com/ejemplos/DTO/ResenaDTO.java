package com.ejemplos.DTO;

import java.util.Date;
import java.util.List;

import com.ejemplos.modelo.Resena;
import com.ejemplos.modelo.Usuario;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ResenaDTO {
	
	@Getter
	@Setter
	private int id;
	
	@Getter
	@Setter
	private String comentario;

	@Getter
	@Setter
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Getter
	@Setter
	private int puntuacion;
	
	@Getter
	@Setter
	private String autor;
	
	@Getter
	@Setter
	private String nombrePlato;



	public ResenaDTO(Resena resena) {
		this.id=resena.getId();
		this.comentario=resena.getComentario();
		this.fecha=resena.getFecha();
		this.puntuacion=resena.getPuntuacion();
		this.autor=resena.getUsuario().getNombre();
		this.nombrePlato=resena.getPlato().getNombre();

	}



	public ResenaDTO() {
		
	}
	
	

}
