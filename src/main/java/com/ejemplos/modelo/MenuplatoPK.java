package com.ejemplos.modelo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the menuplatos database table.
 * 
 */
@Embeddable
public class MenuplatoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int platoid;

	@Column(insertable=false, updatable=false)
	private int idmenu;

	private String momentodia;

	public MenuplatoPK() {
	}
    public MenuplatoPK(Integer platoid, Integer idmenu, String momentodia) {
        this.platoid = platoid;
        this.idmenu = idmenu;
        this.momentodia = momentodia;
    }
	public int getPlatoid() {
		return this.platoid;
	}
	public void setPlatoid(int platoid) {
		this.platoid = platoid;
	}
	public int getIdmenu() {
		return this.idmenu;
	}
	public void setIdmenu(int idmenu) {
		this.idmenu = idmenu;
	}
	public String getMomentodia() {
		return this.momentodia;
	}
	public void setMomentodia(String momentodia) {
		this.momentodia = momentodia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MenuplatoPK)) {
			return false;
		}
		MenuplatoPK castOther = (MenuplatoPK)other;
		return 
			(this.platoid == castOther.platoid)
			&& (this.idmenu == castOther.idmenu)
			&& this.momentodia.equals(castOther.momentodia);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.platoid;
		hash = hash * prime + this.idmenu;
		hash = hash * prime + this.momentodia.hashCode();
		
		return hash;
	}
}