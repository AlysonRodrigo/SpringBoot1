package com.farmacia.farmacia.Controller;

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

import com.farmacia.farmacia.Model.categoriaModel;
import com.farmacia.farmacia.Repository.categoriaRepository;

@RestController
@RequestMapping("/categoria")
public class categoriaController {
	public @Autowired categoriaRepository repositorios;

	@GetMapping("/todas")
	public ResponseEntity<List<categoriaModel>> pegarTodas() {
		List<categoriaModel> objetoLista = repositorios.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/{id_Categoria}")
	public ResponseEntity<categoriaModel> buscarPorId(@PathVariable(value = "idCategoria") Long idCategoria) {
		Optional<categoriaModel> objetoCategoria = repositorios.findById(idCategoria);

		if (objetoCategoria.isPresent()) {
			return ResponseEntity.status(200).body(objetoCategoria.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/remedio/{categoria}")
	public ResponseEntity<List<categoriaModel>> buscarPorCategoria(
			@PathVariable(value = "nome_categoria") String tipo_De_Dor) {
		List<categoriaModel> objetoLista = repositorios.findAllByTipoDeDorContainingIgnoreCase(tipo_De_Dor);
		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody categoriaModel novoCategoria) {

		return ResponseEntity.status(201).body(repositorios.save(novoCategoria));
	}

	@PutMapping("/atualizar_categoria")
	public ResponseEntity<categoriaModel> atualizar(@Valid @RequestBody categoriaModel categoriaParaAtualizar) {
		return ResponseEntity.status(201).body(repositorios.save(categoriaParaAtualizar));
	}

	@DeleteMapping("/deletar/{idCategoria}")
	public void deletarCategoriaPorId(@PathVariable(value = "idProduto") Long idCategoria) {
		repositorios.deleteById(idCategoria);
	}

}
