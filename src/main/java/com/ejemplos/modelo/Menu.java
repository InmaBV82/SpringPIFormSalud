package com.ejemplos.modelo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@Table(name="menu")
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Menuplato
//	@JsonIgnore
//	@OneToMany(mappedBy="menu")
//	private List<Menuplato> menuplatos;
	
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="tipoid")
	private Tipo tipo;
	

	public Menu() {
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

//	public List<Menuplato> getMenuplatos() {
//		return this.menuplatos;
//	}
//
//	public void setMenuplatos(List<Menuplato> menuplatos) {
//		this.menuplatos = menuplatos;
//	}
//
//	public Menuplato addMenuplato(Menuplato menuplato) {
//		getMenuplatos().add(menuplato);
//		menuplato.setMenu(this);
//
//		return menuplato;
//	}
//
//	public Menuplato removeMenuplato(Menuplato menuplato) {
//		getMenuplatos().remove(menuplato);
//		menuplato.setMenu(null);
//
//		return menuplato;
//	}
	
	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


}