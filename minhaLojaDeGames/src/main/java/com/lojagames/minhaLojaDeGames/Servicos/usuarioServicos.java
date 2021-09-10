package com.lojagames.minhaLojaDeGames.Servicos;

import java.nio.charset.Charset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lojagames.minhaLojaDeGames.model.usuarioModel;
import com.lojagames.minhaLojaDeGames.model.Utilidades.usuarioOTO;
import com.lojagames.minhaLojaDeGames.repository.usuarioRepository;


@Service
public class usuarioServicos {
	private @Autowired usuarioRepository repositorio;
	public Optional<Object> cadastroUsuario(usuarioModel novoUsuario) {

		return repositorio.findByEmail(novoUsuario.getEmail()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String result = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(result);
			return Optional.ofNullable(repositorio.save(novoUsuario));
		});
	}

	public Optional<?> pegarCredenciais(usuarioOTO usuarioParaAutenticar) {
		return repositorio.findByEmail(usuarioParaAutenticar.getEmail()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(usuarioParaAutenticar.getSenha(), usuarioExistente.getSenha())) {

				String estruturaBasic = usuarioParaAutenticar.getEmail() + ":" + usuarioParaAutenticar.getSenha();
				byte[] autorizacaoBase64 = (estruturaBasic.getBytes(Charset.forName("US-ASCII")));
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64);

				usuarioParaAutenticar.setToken(autorizacaoHeader);
				usuarioParaAutenticar.setId(usuarioExistente.getId());
				usuarioParaAutenticar.setNome(usuarioExistente.getNome());
				usuarioParaAutenticar.setSenha(usuarioExistente.getSenha());
				return Optional.ofNullable(usuarioParaAutenticar);

			} else {
				return Optional.empty();
			}

		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
