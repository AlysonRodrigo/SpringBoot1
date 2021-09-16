/*package com.blogpessoal.MeuBlog.Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class usuarioTest {

public usuarioModel usuario;
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
		usuario = new usuarioModel(0L,"Chris Silva", "chris@email.com", "12345678");
		
	}
	
	@Test
	void validaUsuarioCorretoRetornaTrue() {
		Set<ConstraintViolation<usuarioModel>> objetoViolacao = validator.validate(usuario);
		assertTrue(objetoViolacao.isEmpty());
	}
	
	@Test
	void validaNomeDeUsuarioIncorretoRetornaFalse() {
		usuarioModel usuarioComFalha = new usuarioModel(0L," ", "lion@email.com", "1829374");
		Set<ConstraintViolation<usuarioModel>> objetoViolacao = validator.validate(usuarioComFalha);
		assertFalse(objetoViolacao.isEmpty());
	}
}
*/