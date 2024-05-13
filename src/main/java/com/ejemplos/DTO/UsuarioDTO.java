package com.ejemplos.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class UsuarioDTO {
	
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	private String password;

	

	public UsuarioDTO() {
		
	}
	
	

}
