package com.ejemplos.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ejemplos.DTO.PlatoDTO;
import com.ejemplos.modelo.Plato;

public interface PlatoRepositorio extends JpaRepository<Plato, Integer> {
	@Query("SELECT p FROM Plato p WHERE p.categoria.id = :categoriaid")
    List<Plato> findPlatosDeCategoria(@Param("categoriaid") int categoriaid);
	


}
