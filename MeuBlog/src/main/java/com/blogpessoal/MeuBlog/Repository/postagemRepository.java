package com.blogpessoal.MeuBlog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoal.MeuBlog.Model.postagemModel;

@Repository
public interface postagemRepository extends JpaRepository<postagemModel, Long> {
	List<postagemModel> findAllByTituloContainingIgnoreCase(String titulo);

}
