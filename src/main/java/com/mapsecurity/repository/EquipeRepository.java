package com.mapsecurity.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mapsecurity.entities.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
	//Query Method
	List<Equipe>findByNome(String nome);
	
	//JPQL
	@Query("SELECT a FROM Equipe a WHERE a.cidade = :cidade")
	List<Equipe> findByCidade(@Param("cidade") String cidade);
	

}
