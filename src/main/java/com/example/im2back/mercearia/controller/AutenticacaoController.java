package com.example.im2back.mercearia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.im2back.mercearia.model.usuario.DadosAutenticacao;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;
	

	@PostMapping
	public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados) {
		
		var token = new  UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
			var authentication  = manager.authenticate(token);
				return ResponseEntity.ok().build();
				}
}
