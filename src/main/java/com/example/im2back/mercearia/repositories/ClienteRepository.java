package com.example.im2back.mercearia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.im2back.mercearia.model.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
