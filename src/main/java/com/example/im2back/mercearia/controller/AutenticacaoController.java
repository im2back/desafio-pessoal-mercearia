package com.example.im2back.mercearia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.im2back.mercearia.infra.exceptions.ServiceExceptions;
import com.example.im2back.mercearia.infra.security.TokenService;
import com.example.im2back.mercearia.model.usuario.DadosAutenticacao;
import com.example.im2back.mercearia.model.usuario.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		var token = request.getParameter("token");
		return "redirect:/login";
	}

	@GetMapping
	public String telaDeLogin(HttpServletRequest request) {
		return "cliente/Template-FormularioLogin";
	}

	@PostMapping
	public String login(@Valid DadosAutenticacao dados, Model model, HttpServletResponse response,HttpServletRequest request) {	

		var tokenAuthentication = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		Authentication authentication = null;
		try {
		authentication = manager.authenticate(tokenAuthentication);
		} catch (RuntimeException e) {
			throw new ServiceExceptions("Usuario Invalido");
		}
			String token = tokenService.gerarToken((Usuario) authentication.getPrincipal());
			model.addAttribute("token", token);
				return "cliente/Template-Home";
	}
}
