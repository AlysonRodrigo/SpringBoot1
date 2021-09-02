package com.blogpessoal.MeuBlog.Controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.MeuBlog.Model.postagemModel;
import com.blogpessoal.MeuBlog.Repository.postagemRepository;

@RestController
@RequestMapping("/postagem")
public class postagemController {
	private @Autowired postagemRepository repositorio;

	@GetMapping("/todas")
	public ResponseEntity<List<postagemModel>> pegarTodas() {
		List<postagemModel> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody postagemModel novoCategoria) {

		return ResponseEntity.status(201).body(repositorio.save(novoCategoria));
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<postagemModel>> buscarPorTituloI(@PathVariable(value = "titulo") String titulo) {
		List<postagemModel> objetoLista = repositorio.findAllByTituloContainingIgnoreCase(titulo);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/pesquisa")
	public ResponseEntity<List<postagemModel>> buscarPorTituloII(@RequestParam(defaultValue = "") String titulo) {
		List<postagemModel> objetoLista = repositorio.findAllByTituloContainingIgnoreCase(titulo);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PutMapping("/atualizar")
	public ResponseEntity<postagemModel> atualizar(@Valid @RequestBody postagemModel postagemParaAtualizar) {
		return ResponseEntity.status(201).body(repositorio.save(postagemParaAtualizar));
	}

	@DeleteMapping("/deletar/{id_postagem}")
	public void deletarPostagemPorId(@PathVariable(value = "id_postagem") Long idPostagem) {
		repositorio.deleteById(idPostagem);
	}
}
