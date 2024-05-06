package com.ejemplos.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplos.modelo.Tipo;


public interface TipoMenuRepositorio extends JpaRepository<Tipo, Integer>{

}
