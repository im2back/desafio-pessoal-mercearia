package com.example.im2back.mercearia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.im2back.mercearia.repositories.UsuarioRepository;

public class AutenticacaoService implements UserDetailsService {

	@Autowired
	UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return repository.findByLogin(username);
	}

}
