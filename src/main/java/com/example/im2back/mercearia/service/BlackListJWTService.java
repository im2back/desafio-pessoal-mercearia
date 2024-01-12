package com.example.im2back.mercearia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.im2back.mercearia.infra.security.BlackListJWT;
import com.example.im2back.mercearia.repositories.BlackListJWTRepository;

@Service
public class BlackListJWTService {
	@Autowired
	private BlackListJWTRepository repository;
	
	
	public String findByToken(String token){
		List<String> listaTokens = blackList();
		boolean estaPresente = listaTokens.contains(token);

	if(estaPresente) {
		return "invalido";
	} else {
		return "valido";
	}	
	}
	
	private List<String> blackList(){
		var blackListObject = repository.findAll();
		List<String> tokens = new ArrayList<>();
	
		for(BlackListJWT t: blackListObject) {
			tokens.add(t.getToken());
		}
		
		return tokens;
		
	}
	
	public void saveToken(String token) {
		repository.save(new BlackListJWT(token));	
	}
	
	
}
