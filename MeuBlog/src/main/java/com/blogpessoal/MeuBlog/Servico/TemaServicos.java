package com.blogpessoal.MeuBlog.Servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.MeuBlog.Model.temaModel;
import com.blogpessoal.MeuBlog.Repository.temaRepository;

@Service
public class TemaServicos {
	private @Autowired temaRepository repository;

	public Optional<temaModel> atualizarTema(temaModel temaParaAlterar) {
		return repository.findById(temaParaAlterar.getIdTema()).map(temaExistente -> {
			temaExistente.setTema(temaParaAlterar.getTema());
			return Optional.ofNullable(repository.save(temaExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
