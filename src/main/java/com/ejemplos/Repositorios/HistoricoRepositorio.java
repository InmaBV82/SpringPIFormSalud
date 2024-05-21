package com.ejemplos.Repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ejemplos.modelo.Historico;

import jakarta.transaction.Transactional;

public interface HistoricoRepositorio extends JpaRepository<Historico, Integer> {
	
	@Query("SELECT h FROM Historico h WHERE h.usuario.id = :usuarioid")
    List<Historico> findHistoricosUsuario(@Param("usuarioid") int usuarioid);
	
	@Modifying//indica que realiza una modificación no sólo consulta
	@Transactional
	@Query("DELETE FROM Historico h WHERE h.usuario.id = :id")
	void deleteByAutorId( int id);
	



}
