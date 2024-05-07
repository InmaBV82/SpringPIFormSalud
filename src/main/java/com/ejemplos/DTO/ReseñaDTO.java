package com.ejemplos.DTO;

import java.util.Date;
import java.util.List;

import com.ejemplos.modelo.Reseña;
import com.ejemplos.modelo.Usuario;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ReseñaDTO {
	
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



	public ReseñaDTO(Reseña reseña) {
		this.id=reseña.getId();
		this.comentario=reseña.getComentario();
		this.fecha=reseña.getFecha();
		this.puntuacion=reseña.getPuntuacion();
		this.autor=reseña.getUsuario().getNombre();

	}



	public ReseñaDTO() {
		
	}
	
	

}
