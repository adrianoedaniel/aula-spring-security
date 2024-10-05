package com.mapsecurity.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapsecurity.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda,Long>{

	//Query Method
	List<Venda>findByData(Date data);

}

