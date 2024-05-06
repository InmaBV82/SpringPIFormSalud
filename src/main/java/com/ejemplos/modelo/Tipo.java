package com.ejemplos.modelo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


/**
 * The persistent class for the tipo database table.
 * 
 */
@Entity
@Table(name="tipo")
@NamedQuery(name="Tipo.findAll", query="SELECT t FROM Tipo t")
public class Tipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descripcion;

	private String nombre;
	
	//bi-directional many-to-one association to menu
	@JsonIgnore
	@OneToMany(mappedBy="tipo")
	private List<Menu> menu;

	public Tipo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public List<Menu> getMenu() {
		return this.menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	
}