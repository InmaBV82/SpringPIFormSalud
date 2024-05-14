package com.ejemplos.DTO;

import java.util.Date;

import com.ejemplos.modelo.Historico;
import com.ejemplos.modelo.HistoricoPK;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class HistoricoDTO {
	
//	@Getter
//	@Setter
//	private HistoricoPK id;
	
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
	
//	@Getter
//	@Setter
//	private int usuarioid;


	public HistoricoDTO(Historico historico) {
		//this.id=historico.getId();
		this.fecha=historico.getId().getFecha();
		this.momentodia=historico.getMomentodia();
		this.platoid=historico.getPlato().getId();
		this.nombrePlato=historico.getPlato().getNombre();
		//this.usuarioid=historico.getUsuario().getId();

	}



	public HistoricoDTO() {
		
	}
	
	

}
