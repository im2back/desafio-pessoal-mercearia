package com.example.im2back.mercearia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.im2back.mercearia.infra.security.BlackListJWT;

public interface BlackListJWTRepository extends JpaRepository<BlackListJWT, Long> {
	
	Optional<BlackListJWT> findByToken(String token);

}
