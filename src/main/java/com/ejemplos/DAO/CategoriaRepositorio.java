package com.ejemplos.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ejemplos.modelo.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {
	@Query("SELECT c FROM Categoria c ORDER BY c.id")
	List<Categoria> findAllOrderedById();

}
