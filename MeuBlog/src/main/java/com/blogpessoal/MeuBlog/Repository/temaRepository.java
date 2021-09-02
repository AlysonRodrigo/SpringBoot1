package com.blogpessoal.MeuBlog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoal.MeuBlog.Model.temaModel;

@Repository
public interface temaRepository extends JpaRepository<temaModel, Long> {
	List<temaModel> findAllByTemaContainingIgnoreCase(String tema);
}
