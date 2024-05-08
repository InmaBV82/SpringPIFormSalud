package com.ejemplos.modelo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the plato database table.
 * 
 */
@Entity
@Table(name="plato")
@NamedQuery(name="Plato.findAll", query="SELECT p FROM Plato p")
public class Plato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	private String nombre;

	private String descripcion;

	private boolean esreceta;

	private String foto;

	private String ingredientes;

	private int tiempo;

	//bi-directional many-to-one association to Historico
//	@OneToMany(mappedBy="plato")
//	private List<Historico> historicos;

	//bi-directional many-to-one association to Menuplato
//	@OneToMany(mappedBy="plato")
//	private List<Menuplato> menuplatos;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="categoriaid")
	private Categoria categoria;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="autorid")
	private Usuario autor;

//	bi-directional many-to-one association to Reseña
	//@JsonIgnore
	@OneToMany(mappedBy="plato", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Resena> resenas;

	public Plato() {
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


	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getEsreceta() {
		return this.esreceta;
	}

	public void setEsreceta(boolean esreceta) {
		this.esreceta = esreceta;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getIngredientes() {
		return this.ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public int getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

//	public List<Historico> getHistoricos() {
//		return this.historicos;
//	}
//
//	public void setHistoricos(List<Historico> historicos) {
//		this.historicos = historicos;
//	}
//
//	public Historico addHistorico(Historico historico) {
//		getHistoricos().add(historico);
//		historico.setPlato(this);
//
//		return historico;
//	}
//
//	public Historico removeHistorico(Historico historico) {
//		getHistoricos().remove(historico);
//		historico.setPlato(null);
//
//		return historico;
//	}
//
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
//		menuplato.setPlato(this);
//
//		return menuplato;
//	}
//
//	public Menuplato removeMenuplato(Menuplato menuplato) {
//		getMenuplatos().remove(menuplato);
//		menuplato.setPlato(null);
//
//		return menuplato;
//	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getAutor() {
		return this.autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public List<Resena> getResenas() {
		return this.resenas;
	}

	public void setResenas(List<Resena> resenas) {
		this.resenas = resenas;
	}

	public Resena addResena(Resena resena) {
		getResenas().add(resena);
		resena.setPlato(this);

		return resena;
	}

	public Resena removeResena(Resena resena) {
		getResenas().remove(resena);
		resena.setPlato(null);

		return resena;
	}

}