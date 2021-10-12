package com.blogpessoal.MeuBlog.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class postagemModel {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idPostagem;
	private String titulo;
	private String descricao;

	private Date date = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JoinColumn(name = "criadores")
	@JsonIgnoreProperties({ "minhasPostagens" })
	private usuarioModel criador;

	@ManyToOne
	@JoinColumn(name = "tema")
	@JsonIgnoreProperties({"postagens"})
	private temaModel temaRelacionados;

	public temaModel getTemaRelacionados() {
		return temaRelacionados;
	}

	public void setTemaRelacionados(temaModel temaRelacionados) {
		this.temaRelacionados = temaRelacionados;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public usuarioModel getCriador() {
		return criador;
	}

	public void setCriador(usuarioModel criador) {
		this.criador = criador;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public Long getIdPostagem() {
		return idPostagem;
	}
	

}
