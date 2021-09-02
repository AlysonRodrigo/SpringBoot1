package com.blogpessoal.MeuBlog.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class temaModel {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long idTema;
	private String tema;
	public long getIdTema() {
		return idTema;
	}
	public void setIdTema(long idTema) {
		this.idTema = idTema;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	
}
