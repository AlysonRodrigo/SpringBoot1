package com.blogpessoal.MeuBlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping("/")
@SpringBootApplication
public class MeuBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeuBlogApplication.class, args);
	}
	@GetMapping
	public ModelAndView swaggerUi() {
		return new ModelAndView("redirect:/swagger-ui/");
	}
}
