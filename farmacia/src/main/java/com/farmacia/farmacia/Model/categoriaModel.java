package com.farmacia.farmacia.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class categoriaModel {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idCategoria;
	private String tipoDeDor;
	private String comoUsar;

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getTipoDeDor() {
		return tipoDeDor;
	}

	public void setTipoDeDor(String tipoDeDor) {
		this.tipoDeDor = tipoDeDor;
	}

	public String getComoUsar() {
		return comoUsar;
	}

	public void setComoUsar(String comoUsar) {
		this.comoUsar = comoUsar;
	}

}
