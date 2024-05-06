package com.ejemplos.modelo;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the historico database table.
 * 
 */
@Entity
@Table(name="historico")
@NamedQuery(name="Historico.findAll", query="SELECT h FROM Historico h")
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HistoricoPK id;

	private String momentodia;

	//bi-directional many-to-one association to Plato
	@ManyToOne
	@JoinColumn(name="platoid")
	private Plato plato;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuarioid")
	private Usuario usuario;

	public Historico() {
	}

	public HistoricoPK getId() {
		return this.id;
	}

	public void setId(HistoricoPK id) {
		this.id = id;
	}

	public String getMomentodia() {
		return this.momentodia;
	}

	public void setMomentodia(String momentodia) {
		this.momentodia = momentodia;
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