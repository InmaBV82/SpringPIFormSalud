package com.ejemplos.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ejemplos.modelo.Resena;


public interface ResenaRepositorio extends JpaRepository<Resena, Integer>{
	@Query("SELECT r FROM Resena r WHERE r.plato.id = :platoid")
    List<Resena> findResenasPlato(@Param("platoid") int platoid);
	
	@Query("SELECT r FROM Resena r WHERE r.usuario.id = :usuarioid")
    List<Resena> findResenasUsuario(@Param("usuarioid") int usuarioid);
	

}
