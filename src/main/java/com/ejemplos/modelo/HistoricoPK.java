package com.ejemplos.modelo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the historico database table.
 * 
 */
@Embeddable
public class HistoricoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int platoid;

	@Column(insertable=false, updatable=false)
	private int usuarioid;

	@Temporal(TemporalType.DATE)
	private java.util.Date fecha;

	public HistoricoPK() {
	}
	public int getPlatoid() {
		return this.platoid;
	}
	public void setPlatoid(int platoid) {
		this.platoid = platoid;
	}
	public int getUsuarioid() {
		return this.usuarioid;
	}
	public void setUsuarioid(int usuarioid) {
		this.usuarioid = usuarioid;
	}
	public java.util.Date getFecha() {
		return this.fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HistoricoPK)) {
			return false;
		}
		HistoricoPK castOther = (HistoricoPK)other;
		return 
			(this.platoid == castOther.platoid)
			&& (this.usuarioid == castOther.usuarioid)
			&& this.fecha.equals(castOther.fecha);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.platoid;
		hash = hash * prime + this.usuarioid;
		hash = hash * prime + this.fecha.hashCode();
		
		return hash;
	}
}