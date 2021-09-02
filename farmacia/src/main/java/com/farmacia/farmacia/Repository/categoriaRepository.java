package com.farmacia.farmacia.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.farmacia.Model.categoriaModel;

@Repository
public interface categoriaRepository extends JpaRepository<categoriaModel, Long> {
	List<categoriaModel> findAllByTipoDeDorContainingIgnoreCase(String tipoDeDor);

	List<categoriaModel> findAllByComoUsarContainingIgnoreCase(String comoUsar);
}
