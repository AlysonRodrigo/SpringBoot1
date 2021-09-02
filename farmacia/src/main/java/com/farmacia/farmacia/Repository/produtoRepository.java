package com.farmacia.farmacia.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.farmacia.Model.produtoModel;

@Repository
public interface produtoRepository extends JpaRepository<produtoModel, Long> {
	List<produtoModel> findAllByNomeProdutoContainingIgnoreCase(String nomeProduto);

	List<produtoModel> findAllByPreco(double preco);

}
