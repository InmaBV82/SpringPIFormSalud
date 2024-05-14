package com.ejemplos.Repositorios;





import org.springframework.data.jpa.repository.JpaRepository;


import com.ejemplos.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	
	Usuario findByEmailAndPassword(String email, String password);

	//Optional<Usuario> findByEmail(String email);
	

}
