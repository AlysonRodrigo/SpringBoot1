package com.blogpessoal.MeuBlog.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoal.MeuBlog.Model.usuarioModel;

@Repository
public interface usuarioRepository extends JpaRepository<usuarioModel, Long> {
	List<usuarioModel> findAllByNomeContainingIgnoreCase(String nome);

	Optional<usuarioModel> findByEmail(String email);
}
