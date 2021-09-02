package com.blogpessoal.MeuBlog.Controller;

import java.util.List;
import java.util.Optional;

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

import com.blogpessoal.MeuBlog.Model.temaModel;
import com.blogpessoal.MeuBlog.Repository.temaRepository;

@RestController
@RequestMapping("/tema")
public class temaController {
	public @Autowired temaRepository repositorio;

	@GetMapping("/todas")
	public ResponseEntity<List<temaModel>> pegarTodas() {
		List<temaModel> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<temaModel> salvar(@Valid @RequestBody temaModel novoTema) {
		return ResponseEntity.status(201).body(repositorio.save(novoTema));
	}

	@GetMapping("/{id_tema}")
	public ResponseEntity<temaModel> buscarPorId(@PathVariable(value = "id_tema") Long idTema) {
		Optional<temaModel> objetoTema = repositorio.findById(idTema);

		if (objetoTema.isPresent()) {
			return ResponseEntity.status(200).body(objetoTema.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/tema/{tema}")
	public ResponseEntity<List<temaModel>> buscarPorTemaI(@PathVariable(value = "tema") String tema) {
		List<temaModel> objetoLista = repositorio.findAllByTemaContainingIgnoreCase(tema);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/pesquisa")
	public ResponseEntity<List<temaModel>> buscarPorTemaII(@RequestParam(defaultValue = "") String tema) {
		List<temaModel> objetoLista = repositorio.findAllByTemaContainingIgnoreCase(tema);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PutMapping("/atualizar")
	public ResponseEntity<temaModel> atualizar(@Valid @RequestBody temaModel temaParaAtualizar) {
		return ResponseEntity.status(201).body(repositorio.save(temaParaAtualizar));
	}

	@DeleteMapping("/deletar/{id_tema}")
	public void deletarTemaPorId(@PathVariable(value = "id_tema") Long idTema) {
		repositorio.deleteById(idTema);
	}
}
