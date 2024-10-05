package com.mapsecurity.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mapsecurity.entities.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{
	//Query Method
	List<Vendedor>findByNome(String nome);

	//JPQL
	@Query("SELECT a FROM Vendedor a WHERE a.salario = :salario")
	List<Vendedor> findBySalario(@Param("salario") Double salario);

}
