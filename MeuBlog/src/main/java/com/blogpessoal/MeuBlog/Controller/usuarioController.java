package com.blogpessoal.MeuBlog.Controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.MeuBlog.Model.usuarioModel;
import com.blogpessoal.MeuBlog.Model.Utilidades.usuarioDTO;
import com.blogpessoal.MeuBlog.Repository.usuarioRepository;
import com.blogpessoal.MeuBlog.Servico.usuarioServicos;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/usuario")
@Api(tags = "Controlador de Postagem", description = "Utilitario de Postagens")
@CrossOrigin("*")
public class usuarioController {
	private @Autowired usuarioRepository repositorio;
	private @Autowired usuarioServicos servicos;

	@GetMapping("/todes")
	public ResponseEntity<List<usuarioModel>> pegarTodes() {
		List<usuarioModel> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}

	}

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody usuarioModel novoUsuario) {
		Optional<Object> objetoOptional = servicos.cadastroUsuario(novoUsuario);

		if (objetoOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).body(objetoOptional.get());
		}
	}

	@PutMapping("/credenciais")
	public ResponseEntity<Object> credenciais(@Valid @RequestBody usuarioDTO usuarioParaAutenticar) {
		Optional<?> objetoOptional = servicos.pegarCredenciais(usuarioParaAutenticar);

		if (objetoOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).body(objetoOptional.get());
		}
	}

	@GetMapping("/{id_usuario}")
	public ResponseEntity<usuarioModel> buscarPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
		Optional<usuarioModel> objetoUsuario = repositorio.findById(idUsuario);

		if (objetoUsuario.isPresent()) {
			return ResponseEntity.status(200).body(objetoUsuario.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/nome/{nome_usuario}")
	public ResponseEntity<List<usuarioModel>> buscarPorNomeI(@PathVariable(value = "nome_usuario") String nome) {
		List<usuarioModel> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/pesquisa")
	public ResponseEntity<List<usuarioModel>> buscarPorNomeII(@RequestParam(defaultValue = "") String nome) {
		List<usuarioModel> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Object> alterar(@Valid @RequestBody usuarioDTO usuarioParaAlterar) {
		Optional<?> objetoAlterado = servicos.alterarUsuario(usuarioParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}
	@DeleteMapping("/deletar/{id_usuario}")
	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
		Optional<usuarioModel> objetoExistente = repositorio.findById(idUsuario);
		if (objetoExistente.isPresent()) {
			repositorio.deleteById(idUsuario);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}

	}
}
