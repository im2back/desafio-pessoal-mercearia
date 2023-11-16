package com.example.im2back.mercearia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.im2back.mercearia.model.usuario.DadosAutenticacao;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;
	
	@GetMapping
	public String telaDeLogin() {
		return "cliente/Formulario-LOGIN";	
		}
	
	@PostMapping
	public String login(@Valid DadosAutenticacao dados, Model model) {
		
		var token = new  UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
			@SuppressWarnings("unused")
			var authentication  = manager.authenticate(token);
					return "ok";//return "cliente/home";
				}
}
