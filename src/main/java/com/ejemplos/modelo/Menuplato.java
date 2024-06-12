package com.ejemplos.modelo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


/**
 * The persistent class for the menuplatos database table.
 * 
 */
@Entity
@Table(name="menuplatos")
@NamedQuery(name="Menuplato.findAll", query="SELECT m FROM Menuplato m")
public class Menuplato implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MenuplatoPK id;

	//bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name="idmenu", insertable = false, updatable = false)
	@JsonIgnore // Ignorar durante la serialización al crear el menuplato
	private Menu menu;

	//bi-directional many-to-one association to Plato
	@ManyToOne
	@JoinColumn(name="platoid", insertable = false, updatable = false)
	@JsonIgnore // Ignorar durante la serialización al crear el menuplato
	private Plato plato;

	public Menuplato() {
	}

	public MenuplatoPK getId() {
		return this.id;
	}

	public void setId(MenuplatoPK id) {
		this.id = id;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Plato getPlato() {
		return this.plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

}