package com.ejemplos.DTO;

import java.util.Date;
import java.util.List;

import com.ejemplos.modelo.Plato;
import com.ejemplos.modelo.Resena;
import com.ejemplos.modelo.Usuario;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ResenaAddDTO {
	
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
	private int autorId;
	
	@Getter
	@Setter
	private int platoId;



	public ResenaAddDTO(Resena resena) {
		this.id=resena.getId();
		this.comentario=resena.getComentario();
		this.fecha=resena.getFecha();
		this.puntuacion=resena.getPuntuacion();
		this.autorId=resena.getUsuario().getId();
		this.platoId=resena.getPlato().getId();

	}



	public ResenaAddDTO() {
		
	}
	
	

}
