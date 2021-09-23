package com.blogpessoal.MeuBlog.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class postagemModel {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long idPostagem;
	private String titulo;
	private String descricao;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPostagem = LocalDate.now();

	

	@ManyToOne
	@JoinColumn(name = "criadores")
	@JsonIgnoreProperties({ "minhasPostagens" })
	private usuarioModel criador;

	@ManyToOne
	@JoinColumn(name = "Temas")
	@JsonIgnoreProperties({ "postagens" })
	private temaModel TemasRelacionados;

	public temaModel getTemasRelacionados() {
		return TemasRelacionados;
	}

	public void setTemasRelacionados(temaModel temasRelacionados) {
		TemasRelacionados = temasRelacionados;
	}

	public long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(long idPostagem) {
		this.idPostagem = idPostagem;
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
	public LocalDate getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(LocalDate dataPostagem) {
		this.dataPostagem = dataPostagem;
	}
}
