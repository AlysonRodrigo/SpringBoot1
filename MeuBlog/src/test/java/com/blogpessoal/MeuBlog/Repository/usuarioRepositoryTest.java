package com.blogpessoal.MeuBlog.Repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.blogpessoal.MeuBlog.Model.usuarioModel;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class usuarioRepositoryTest {
	private @Autowired usuarioRepository repositorio;
	@BeforeEach
	void start() {
		
		usuarioModel objeto1 = new usuarioModel(0L,"Charles Silva", "charles@email.com", "2745593");
		if(!repositorio.findByEmail(objeto1.getEmail()).isPresent())
			repositorio.save(objeto1);
		
		usuarioModel objeto2 = new usuarioModel(0L,"Evelyn Cristine", "evelyn@email.com", "2745593");
		if(!repositorio.findByEmail(objeto2.getEmail()).isPresent())
			repositorio.save(objeto2);
	}
	@Test
	void findByEmailExistenteRetornaTrue() {
		usuarioModel objetoCharles = repositorio.findByEmail("charles@email.com").get();
		
		assertTrue(objetoCharles.getEmail().equals("charles@email.com"));
	}
	@Test
	void findAllByNomeContainingIgnoreCaseRetornaRetornaDoisUsuarios() {
		List<usuarioModel> objetoLista = repositorio.findAllByNomeContainingIgnoreCase("Silva");
		
		assertEquals(2, objetoLista.size());
	}
	
	@AfterAll
	void end() {
		System.out.println("Teste Finalizado!");
	}
}
