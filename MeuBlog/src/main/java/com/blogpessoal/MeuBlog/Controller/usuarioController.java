package com.blogpessoal.MeuBlog.Controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class usuarioController {
	@Autowired
	private usuarioRepository repository;
	
	@Autowired
	private usuarioServicos usuarioService;

	@GetMapping("/todes")
	public ResponseEntity<List<usuarioModel>> pegarTodes() {
		List<usuarioModel> objetoLista = repository.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}

	}


	@PostMapping("/logar")
	public ResponseEntity<usuarioDTO> Autentication(@RequestBody Optional<usuarioDTO> user) {
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@GetMapping("/{id_usuario}")
	public ResponseEntity<usuarioModel> buscarPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
		Optional<usuarioModel> objetoUsuario = repository.findById(idUsuario);

		if (objetoUsuario.isPresent()) {
			return ResponseEntity.status(200).body(objetoUsuario.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody usuarioModel novoUsuario) {
		Optional<Object> objetoOptional = usuarioService.cadastroUsuario(novoUsuario);

		if (objetoOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).body(objetoOptional.get());
		}
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Object> alterar(@Valid @RequestBody usuarioDTO usuarioParaAlterar) {
		Optional<?> objetoAlterado = usuarioService.alterarUsuario(usuarioParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}
	
	@DeleteMapping("/deletar/{id_usuario}")
	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
		Optional<usuarioModel> objetoExistente = repository.findById(idUsuario);
		if (objetoExistente.isPresent()) {
			repository.deleteById(idUsuario);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}

	}

	@GetMapping("/nome/{nome_usuario}")
	public ResponseEntity<List<usuarioModel>> buscarPorNomeI(@PathVariable(value = "nome_usuario") String nome) {
		List<usuarioModel> objetoLista = repository.findAllByNomeContainingIgnoreCase(nome);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
}
