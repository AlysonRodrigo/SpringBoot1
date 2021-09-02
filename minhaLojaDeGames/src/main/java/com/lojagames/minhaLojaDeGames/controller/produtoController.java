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

import com.lojagames.minhaLojaDeGames.model.produtoModel;
import com.lojagames.minhaLojaDeGames.repository.produtoRepository;

@RestController
@RequestMapping("/produto")
public class produtoController {
	public @Autowired produtoRepository repositorio;

	@GetMapping("/todos")
	public ResponseEntity<List<produtoModel>> pegarTodos() {
		List<produtoModel> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/{id_Produto}")
	public ResponseEntity<produtoModel> buscarPorId(@PathVariable(value = "id_Produto") Long idProduto) {
		Optional<produtoModel> objetoProduto = repositorio.findById(idProduto);

		if (objetoProduto.isPresent()) {
			return ResponseEntity.status(200).body(objetoProduto.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/preco_produto/{produto}")
	public ResponseEntity<List<produtoModel>> buscarPorProduto(
			@PathVariable(value = "produto") double preco_produto) {
		List<produtoModel> objetoLista = repositorio.findAllByPreco(preco_produto);
		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody produtoModel novoProduto) {
		return ResponseEntity.status(201).body(repositorio.save(novoProduto));
	}

	@PutMapping("/atualizar_produto")
	public ResponseEntity<produtoModel> atualizar(@Valid @RequestBody produtoModel produtoParaAtualizar) {
		return ResponseEntity.status(201).body(repositorio.save(produtoParaAtualizar));
	}

	@DeleteMapping("/deletar/{idProduto}")
	public void deletarProdutoPorId(@PathVariable(value = "idProduto") Long idProduto) {
		repositorio.deleteById(idProduto);
	}
}