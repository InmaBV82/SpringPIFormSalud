package com.ejemplos.modelo;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	private String nombre;

	private String password;

	private String rol;

	//bi-directional many-to-one association to Historico
	@OneToMany(mappedBy="usuario")
	@JsonIgnore
	private List<Historico> historicos;

	//bi-directional many-to-one association to Plato
	@JsonIgnore
	@OneToMany(mappedBy="autor")
	private List<Plato> platos;

	//bi-directional many-to-one association to Reseña
	@JsonIgnore
	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Resena> reseñas;

	public Usuario() {

	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<Historico> getHistoricos() {
		return this.historicos;
	}

	public void setHistoricos(List<Historico> historicos) {
		this.historicos = historicos;
	}

	public Historico addHistorico(Historico historico) {
		getHistoricos().add(historico);
		historico.setUsuario(this);

		return historico;
	}

	public Historico removeHistorico(Historico historico) {
		getHistoricos().remove(historico);
		historico.setUsuario(null);

		return historico;
	}

	public List<Plato> getPlatos() {
		return this.platos;
	}

	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}
//
	public Plato addPlato(Plato plato) {
		getPlatos().add(plato);
		plato.setAutor(this);

		return plato;
	}

	public Plato removePlato(Plato plato) {
		getPlatos().remove(plato);
		plato.setAutor(null);

		return plato;
	}

	public List<Resena> getReseñas() {
		return this.reseñas;
	}

	public void setReseñas(List<Resena> reseñas) {
		this.reseñas = reseñas;
	}

	public Resena addReseña(Resena reseña) {
		getReseñas().add(reseña);
		reseña.setUsuario(this);

		return reseña;
	}

	public Resena removeReseña(Resena reseña) {
		getReseñas().remove(reseña);
		reseña.setUsuario(null);

		return reseña;
	}

}