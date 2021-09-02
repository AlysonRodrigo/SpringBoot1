package com.lojagames.minhaLojaDeGames.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.lojagames.minhaLojaDeGames.model.categoria_Model;
import com.lojagames.minhaLojaDeGames.repository.categoria_Repository;

@RestController
@RequestMapping("/categoria")
public class categoria_Controller {
	public @Autowired categoria_Repository repositorios;

	@GetMapping("/todas")
	public ResponseEntity<List<categoria_Model>> pegarTodas() {
		List<categoria_Model> objetoLista = repositorios.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/{id_Categoria}")
	public ResponseEntity<categoria_Model> buscarPorId(@PathVariable(value = "idCategoria") Long idCategoria) {
		Optional<categoria_Model> objetoCategoria = repositorios.findById(idCategoria);

		if (objetoCategoria.isPresent()) {
			return ResponseEntity.status(200).body(objetoCategoria.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/jogo_categoria/{categoria}")
	public ResponseEntity<List<categoria_Model>> buscarPorCategoria(
			@PathVariable(value = "nome_categoria") String nome_Categoria) {
		List<categoria_Model> objetoLista = repositorios.findAllByJogoContainingIgnoreCase(nome_Categoria);
		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody categoria_Model novoCategoria) {

		return ResponseEntity.status(201).body(repositorios.save(novoCategoria));
	}

	@PutMapping("/atualizar_categoria")
	public ResponseEntity<categoria_Model> atualizar(@Valid @RequestBody categoria_Model categoriaParaAtualizar) {
		return ResponseEntity.status(201).body(repositorios.save(categoriaParaAtualizar));
	}

	@DeleteMapping("/deletar/{idCategoria}")
	public void deletarCategoriaPorId(@PathVariable(value = "idProduto") Long idCategoria) {
		repositorios.deleteById(idCategoria);
	}
}