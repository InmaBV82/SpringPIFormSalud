package com.ejemplos.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ejemplos.modelo.Plato;

public interface PlatoRepositorio extends JpaRepository<Plato, Integer> {
	@Query("SELECT p FROM Plato p WHERE p.categoria.id = :categoriaid")
    List<Plato> findPlatosDeCategoria(@Param("categoriaid") int categoriaid);

	@Query("SELECT p FROM Plato p WHERE p.autor.id = :autorid")
	List<Plato> findPlatosUsuario(int autorid);
	


}
