package com.blogpessoal.MeuBlog.Servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.MeuBlog.Model.usuarioModel;
import com.blogpessoal.MeuBlog.Repository.usuarioRepository;

@Service
public class usuarioServicos {
	private @Autowired usuarioRepository repositorio;

	public Optional<Object> cadastroUsuario(usuarioModel novoUsuario) {

		return repositorio.findByEmail(novoUsuario.getEmail()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() ->

		{
			return Optional.ofNullable(repositorio.save(novoUsuario));
		});
	}
}
