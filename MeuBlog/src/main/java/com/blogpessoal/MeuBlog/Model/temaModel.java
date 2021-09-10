package com.blogpessoal.MeuBlog.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class temaModel {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long idTema;
	private String tema;

	@OneToMany(mappedBy = "TemasRelacionados", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"TemasRelacionados"})
	private List<postagemModel>postagens =new ArrayList<>();
	
	
	public List<postagemModel> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<postagemModel> postagens) {
		this.postagens = postagens;
	}

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
