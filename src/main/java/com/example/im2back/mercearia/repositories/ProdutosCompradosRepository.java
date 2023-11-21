package com.example.im2back.mercearia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.im2back.mercearia.model.carrinho.ProdutosComprados;

import jakarta.transaction.Transactional;

public interface ProdutosCompradosRepository extends JpaRepository<ProdutosComprados, Long> {
	
	 @Transactional
	 @Modifying
	 @Query("DELETE FROM ProdutosComprados pc WHERE pc.client.id = :id")
	 void deleteByClient_id(Long id);

	 

}
