package com.blogpessoal.MeuBlog.Servico;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.MeuBlog.Model.postagemModel;
import com.blogpessoal.MeuBlog.Model.temaModel;
import com.blogpessoal.MeuBlog.Repository.postagemRepository;
import com.blogpessoal.MeuBlog.Repository.temaRepository;
import com.blogpessoal.MeuBlog.Repository.usuarioRepository;

@Service
public class postagemServicos {
	private @Autowired postagemRepository repositorioP;
	private @Autowired usuarioRepository repositorioU;
	private @Autowired temaRepository repositorioT;
	
	public Optional<?> cadastrarPostagem(postagemModel novaPostagem) {
		Optional<temaModel> objetoExistente = repositorioT.findById(novaPostagem.getTemaRelacionados().getIdTema());
		return repositorioU.findById(novaPostagem.getCriador().getIdUsuario()).map(usuarioExistente -> {
			if (objetoExistente.isPresent()) {
				novaPostagem.setCriador(usuarioExistente);
				novaPostagem.setTemaRelacionados(objetoExistente.get());
				return Optional.ofNullable(repositorioP.save(novaPostagem));
			} else {
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
	public Optional<postagemModel> alterarPostagem(postagemModel postagemParaAlterar) {
		return repositorioP.findById(postagemParaAlterar.getIdPostagem()).map(postagemExistente -> {
			postagemExistente.setTitulo(postagemParaAlterar.getTitulo());
			postagemExistente.setDescricao(postagemParaAlterar.getDescricao());
			return Optional.ofNullable(repositorioP.save(postagemExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
