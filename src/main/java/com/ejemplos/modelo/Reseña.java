package com.ejemplos.modelo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the reseña database table.
 * 
 */
@Entity
@Table(name="reseña")
@NamedQuery(name="Reseña.findAll", query="SELECT r FROM Reseña r")
public class Reseña implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String comentario;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private int puntuacion;

	//bi-directional many-to-one association to Plato
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="platoid")
	private Plato plato;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuarioid")
	private Usuario usuario;

	public Reseña() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getPuntuacion() {
		return this.puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Plato getPlato() {
		return this.plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}