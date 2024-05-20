package com.ejemplos.DTO;

import java.util.Date;

import com.ejemplos.modelo.Historico;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class HistoricoDTO {
	
	@Getter
	@Setter
	private int idHistorico;
	
	@Getter
	@Setter
	@Temporal(TemporalType.DATE)
	private Date fecha;

	
	@Getter
	@Setter
	private String momentodia;

	@Getter
	@Setter
	private int platoid;
	
	@Getter
	@Setter
	private String nombrePlato;
	
	@Getter
	@Setter
	private int usuarioid;


	public HistoricoDTO(Historico historico) {
		this.idHistorico=historico.getId();
		this.fecha=historico.getFecha();
		this.momentodia=historico.getMomentodia();
		this.platoid=historico.getPlato().getId();
		this.nombrePlato=historico.getPlato().getNombre();
		this.usuarioid=historico.getUsuario().getId();


	}



	public HistoricoDTO() {
		
	}
	
	

}
