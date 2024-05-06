package com.ejemplos.modelo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Table(name="categoria")
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Plato
	@JsonIgnore
	@OneToMany(mappedBy="categoria")
//	@JsonIgnoreProperties({"platos"})
	private List<Plato> platos;

	public Categoria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Plato> getPlatos() {
		return this.platos;
	}
//
	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}

	public Plato addPlato(Plato plato) {
		getPlatos().add(plato);
		plato.setCategoria(this);

		return plato;
	}

	public Plato removePlato(Plato plato) {
		getPlatos().remove(plato);
		plato.setCategoria(null);

		return plato;
	}

}