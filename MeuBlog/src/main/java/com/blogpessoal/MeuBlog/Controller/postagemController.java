package com.blogpessoal.MeuBlog.Controller;

import java.util.List;
import javax.validation.Valid;
import java.util.Optional;

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

import com.blogpessoal.MeuBlog.Model.postagemModel;
import com.blogpessoal.MeuBlog.Repository.postagemRepository;
import com.blogpessoal.MeuBlog.Servico.postagemServicos;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/postagem")
@CrossOrigin("*")
public class postagemController {
	private @Autowired postagemRepository repositorio;
	private @Autowired postagemServicos servicos;

	@GetMapping("/todas")
	public ResponseEntity<List<postagemModel>> pegarTodas() {
		List<postagemModel> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
/*
	@PostMapping("/salvar")
	public ResponseEntity<Object> cadastrarPostagem(@Valid @RequestBody postagemModel novaPostagem) {
		Optional<?> objetoCadastrado = servicos.cadastrarPostagem(novaPostagem);

		if (objetoCadastrado.isPresent()) {
			return ResponseEntity.status(201).body(objetoCadastrado.get());
		} else {
			return ResponseEntity.status(400).build();
		}

	}
/*
	@GetMapping("/{titulo}")
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
	public ResponseEntity<Object> alterar(@Valid @RequestBody postagemModel postagemParaAlterar) {
		Optional<postagemModel> objetoAlterado = servicos.alterarPostagem(postagemParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id_postagem") Long idPostagem) {
		Optional<postagemModel> objetoExistente = repositorio.findById(idPostagem);
		if (objetoExistente.isPresent()) {
			repositorio.deleteById(idPostagem);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}
		
	}*/
	
	@GetMapping
	public ResponseEntity<List<postagemModel>> GetAll(){
		return ResponseEntity.ok(repositorio.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<postagemModel> GetById(@PathVariable long id){
		return repositorio.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<postagemModel>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repositorio.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<postagemModel> post (@RequestBody postagemModel postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<postagemModel> put (@RequestBody postagemModel postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repositorio.deleteById(id);
	}	
}
