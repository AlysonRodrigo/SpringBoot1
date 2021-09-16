/*package com.blogpessoal.MeuBlog.Controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blogpessoal.MeuBlog.Model.usuarioModel;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class usuarioControllerTest {

	private @Autowired TestRestTemplate testrestTemplate;

	private usuarioModel usuarioParaCriar;
	private usuarioModel usuarioParaAlterar;

	@BeforeAll
	void start() {
		usuarioParaCriar = new usuarioModel(0L, "Rodrigo Boaz", "rodrigo@email.com", "342817");
		usuarioParaAlterar = new usuarioModel(1L, "Charles Silva S2", "charles@email.com", "342817");

	}

	@Disabled
	@Test
	void salvaUsuarioNovoNoBancoRetornaStatus201() {

		HttpEntity<usuarioModel> requisicao = new HttpEntity<usuarioModel>(usuarioParaCriar);

		ResponseEntity<usuarioModel> resposta = testrestTemplate.exchange("/api/v1/usuario/salvar", HttpMethod.POST,
				requisicao, usuarioModel.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}

	@Disabled
	@Test
	void buscarTodesRetornaStatus200() {

		ResponseEntity<String> resposta = testrestTemplate.withBasicAuth("rodrigo@email.com", "342817")
				.exchange("/usuario/todes", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Disabled
	@Test
	void atualizarUsuarioNoBancoRetornaStatus201() {

		HttpEntity<usuarioModel> requisicao = new HttpEntity<usuarioModel>(usuarioParaAlterar);

		ResponseEntity<usuarioModel> resposta = testrestTemplate.withBasicAuth("rodrigo@email.com", "342817")
				.exchange("/usuario/atualizar", HttpMethod.PUT, requisicao, usuarioModel.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());

	}
	

}*/