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
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.MeuBlog.Model.temaModel;
import com.blogpessoal.MeuBlog.Repository.temaRepository;
import com.blogpessoal.MeuBlog.Servico.TemaServicos;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class temaController {
	public @Autowired temaRepository repositorio;
	private @Autowired TemaServicos servicos;

	@ApiOperation(value = "Busca lista de temas no sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna lista de temas"),
			@ApiResponse(code = 204, message = "Retorno sem tema") })

	@GetMapping("/todos")
	public ResponseEntity<List<temaModel>> pegarTodas() {
		List<temaModel> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@ApiOperation(value = "Salva novo tema no sistema")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Retorna tema cadastrado") })

	@PostMapping("/salvar")
	public ResponseEntity<temaModel> salvar(@Valid @RequestBody temaModel novoTema) {
		return ResponseEntity.status(201).body(repositorio.save(novoTema));
	}

	@ApiOperation(value = "Busca tema por Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna tema existente"),
			@ApiResponse(code = 204, message = "Retorno inexistente") })
	@GetMapping("/{id_tema}")
	public ResponseEntity<temaModel> buscarPorId(@PathVariable(value = "id_tema") Long idTema) {
		Optional<temaModel> objetoTema = repositorio.findById(idTema);

		if (objetoTema.isPresent()) {
			return ResponseEntity.status(200).body(objetoTema.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@ApiOperation(value = "Busca tema por nome")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna tema existente"),
			@ApiResponse(code = 204, message = "Retorno inexistente") })
	@GetMapping("/{tema}")
	public ResponseEntity<List<temaModel>> buscarPorTemaI(@PathVariable(value = "tema") String tema) {
		List<temaModel> objetoLista = repositorio.findAllByTemaContainingIgnoreCase(tema);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@ApiOperation(value = "Atualizar tema existente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Retorna tema atualizado"),
			@ApiResponse(code = 400, message = "Id de tema invalido") })
	@PutMapping("/atualizar")
	public ResponseEntity<temaModel> atualizar(@Valid @RequestBody temaModel temaParaAtualizar) {
		Optional<temaModel> objetoAlterado = servicos.atualizarTema(temaParaAtualizar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	@DeleteMapping("/deletar/{id_tema}")
	public void deletarTemaPorId(@PathVariable(value = "id_tema") Long idTema) {
		repositorio.deleteById(idTema);
	}
}
