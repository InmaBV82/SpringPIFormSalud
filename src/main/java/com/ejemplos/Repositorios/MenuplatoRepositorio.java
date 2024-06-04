package com.ejemplos.Repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ejemplos.modelo.Menuplato;
import com.ejemplos.modelo.MenuplatoPK;


public interface MenuplatoRepositorio extends JpaRepository<Menuplato, MenuplatoPK> {
	
	@Query("SELECT m FROM Menuplato m WHERE m.menu.tipo.nombre = :tipo")
    List<Menuplato> filtroMenuplatoPorTipo(String tipo);
	
	



}
