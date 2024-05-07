package com.ejemplos.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ejemplos.modelo.Reseña;


public interface ReseñaRepositorio extends JpaRepository<Reseña, Integer>{
	@Query("SELECT r FROM Reseña r WHERE r.plato.id = :platoid")
    List<Reseña> findReseñasPlato(@Param("platoid") int platoid);

}
