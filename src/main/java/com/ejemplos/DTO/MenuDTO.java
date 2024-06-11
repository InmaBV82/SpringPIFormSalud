package com.ejemplos.DTO;


import com.ejemplos.modelo.Menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class MenuDTO {
	
	@Getter
	@Setter
	private int idMenu;
	
	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private int tipoid;
	



	public MenuDTO(Menu menu) {
		this.idMenu=menu.getId();
		this.nombre=menu.getNombre();
		this.tipoid=menu.getTipo().getId();
		


	}



	public MenuDTO() {
		
	}
	
	

}
