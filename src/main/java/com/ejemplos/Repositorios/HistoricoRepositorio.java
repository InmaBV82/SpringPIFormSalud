package com.ejemplos.Repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ejemplos.modelo.Historico;
import com.ejemplos.modelo.HistoricoPK;

public interface HistoricoRepositorio extends JpaRepository<Historico, HistoricoPK> {
	
	@Query("SELECT h FROM Historico h WHERE h.usuario.id = :usuarioid")
    List<Historico> findHistoricosUsuario(@Param("usuarioid") int usuarioid);
	



}
