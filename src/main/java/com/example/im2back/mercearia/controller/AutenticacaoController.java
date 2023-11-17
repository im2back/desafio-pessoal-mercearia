package com.example.im2back.mercearia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.im2back.mercearia.infra.security.TokenDTO;
import com.example.im2back.mercearia.infra.security.TokenService;
import com.example.im2back.mercearia.model.usuario.DadosAutenticacao;
import com.example.im2back.mercearia.model.usuario.Usuario;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@GetMapping
	public String telaDeLogin() {
		return "cliente/Formulario-LOGIN";	
		}
	
	@PostMapping
	public String login(@Valid DadosAutenticacao dados, Model model) {
			var tokenAuthentication = new  UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
			   var authentication  = manager.authenticate(tokenAuthentication);
				    TokenDTO token = new TokenDTO(tokenService.gerarToken((Usuario)authentication.getPrincipal()));
					 model.addAttribute("token",token);
					 	System.out.println("### Token Gerado ### : "+token);
					 		return "cliente/home";

				}
}
