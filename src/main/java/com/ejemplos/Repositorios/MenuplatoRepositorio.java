package com.ejemplos.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ejemplos.modelo.Menuplato;
import com.ejemplos.modelo.MenuplatoPK;

public interface MenuplatoRepositorio extends JpaRepository<Menuplato, MenuplatoPK> {
	



}
