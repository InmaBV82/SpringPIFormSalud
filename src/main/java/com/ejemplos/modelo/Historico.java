package com.ejemplos.modelo;

import java.io.Serializable;
import java.util.Date;

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

	@Id
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;

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


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public void setPlato(Plato i) {
		this.plato = i;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}