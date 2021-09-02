package com.lojagames.minhaLojaDeGames.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lojagames.minhaLojaDeGames.model.categoria_Model;

@Repository
public interface categoria_Repository extends JpaRepository<categoria_Model,Long>  {
	List <categoria_Model> findAllByJogoContainingIgnoreCase(String jogo);
	List<categoria_Model> findByAcessorioContainingIgnoreCase(String acessorio);
}