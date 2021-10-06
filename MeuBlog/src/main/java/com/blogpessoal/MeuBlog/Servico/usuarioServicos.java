package com.blogpessoal.MeuBlog.Servico;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogpessoal.MeuBlog.Model.usuarioModel;
import com.blogpessoal.MeuBlog.Model.Utilidades.usuarioDTO;
import com.blogpessoal.MeuBlog.Repository.usuarioRepository;

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

	public Optional<?> pegarCredenciais(usuarioDTO usuarioParaAutenticar) {
		return repositorio.findByEmail(usuarioParaAutenticar.getEmail()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(usuarioParaAutenticar.getSenha(), usuarioExistente.getSenha())) {

				String estruturaBasic = usuarioParaAutenticar.getEmail() + ":" + usuarioParaAutenticar.getSenha();
				byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII")));
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64);

				usuarioParaAutenticar.setToken(autorizacaoHeader);
				usuarioParaAutenticar.setId(usuarioExistente.getIdUsuario());
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

	public Optional<?> alterarUsuario(usuarioDTO usuarioParaAlterar) {
		return repositorio.findById(usuarioParaAlterar.getId()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuarioParaAlterar.getSenha());

			usuarioExistente.setNome(usuarioParaAlterar.getNome());
			usuarioExistente.setSenha(senhaCriptografada);
			return Optional.ofNullable(repositorio.save(usuarioExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

	public Optional<usuarioDTO> Logar(Optional<usuarioDTO> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<usuarioModel> usuario = repositorio.findByEmail(user.get().getEmail());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getSenha()+ ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);			
				user.get().setId(usuario.get().getIdUsuario());
				user.get().setNome(usuario.get().getNome());
				user.get().setFoto(usuario.get().getFoto());
				user.get().setTipo(usuario.get().getTipo());
				
				return user;

			}
		}
		return null;
	}

}
