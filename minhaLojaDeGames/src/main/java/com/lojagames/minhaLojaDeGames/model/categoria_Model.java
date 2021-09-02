package com.lojagames.minhaLojaDeGames.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class categoria_Model {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idCategoria;
	private String jogo;
	private String acessorio;
	@OneToMany (mappedBy = "liga",cascade = CascadeType.REMOVE)
	private List<produtoModel> ligacao;

	public List<produtoModel> getLigacao() {
		return ligacao;
	}

	public void setLigacao(List<produtoModel> ligacao) {
		this.ligacao = ligacao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getJogo() {
		return jogo;
	}

	public void setJogo(String jogo) {
		this.jogo = jogo;
	}

	public String getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(String acessorio) {
		this.acessorio = acessorio;
	}
}
