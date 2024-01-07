package com.example.im2back.mercearia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.im2back.mercearia.model.cliente.Cliente;

import jakarta.transaction.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Cliente findByDocumento(String documento);
	
	 @Transactional
	 @Modifying
	 @Query("DELETE FROM Cliente c WHERE c.documento = :documento")
	 void deleteByDocumento(String documento);

	


}
