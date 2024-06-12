package com.ejemplos.DTO;


import com.ejemplos.modelo.Menuplato;
import com.ejemplos.modelo.MenuplatoPK;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class MenuPlatoDTO {
	
	@Getter
	@Setter
	private MenuplatoPK id;

	
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
	private int menuid;
	
	@Getter
	@Setter
	private String nombreMenu;
	
	@Getter
	@Setter
	private String tipoMenu;


	public MenuPlatoDTO(Menuplato menuPlato) {
		this.id=menuPlato.getId();
		this.momentodia=menuPlato.getId().getMomentodia();
		this.platoid=menuPlato.getPlato().getId();
		this.nombrePlato=menuPlato.getPlato().getNombre();
		this.menuid=menuPlato.getMenu().getId();
		this.nombreMenu=menuPlato.getMenu().getNombre();
		this.tipoMenu=menuPlato.getMenu().getTipo().getNombre();

	}



	public MenuPlatoDTO() {
		
	}
	
	

}
