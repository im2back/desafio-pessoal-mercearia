package com.example.im2back.mercearia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.im2back.mercearia.model.carrinho.ProdutosComprados;

public interface ProdutosCompradosRepository extends JpaRepository<ProdutosComprados, Long> {

}
