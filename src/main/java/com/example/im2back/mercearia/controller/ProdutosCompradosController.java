package com.example.im2back.mercearia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.im2back.mercearia.event.RecursoCriadoEvento;
import com.example.im2back.mercearia.model.carrinho.ProdutoCompradoRequestDTO;
import com.example.im2back.mercearia.service.ProdutosCompradosService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "produtos")
public class ProdutosCompradosController {
	@Autowired
	private ProdutosCompradosService service;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public String cadastrarProduto(Model model, HttpServletRequest request) {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
			return "produto/Adicionar-Produto-No-Carrinho";
	}

	@PostMapping("/cadastrar")
	@Transactional
	public String salvar(@Valid ProdutoCompradoRequestDTO dtoRequest, HttpServletRequest request, Model model) {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
			service.salvar(dtoRequest);
				return "produto/Adicionar-Produto-No-Carrinho";
	}

	@Transactional
	@PostMapping("/delete")
	public String zerarRegistros(Model model, HttpServletRequest request,HttpServletRequest response) {
		System.out.println("teste");
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
				service.zerarConta(request.getParameter("documento"));
						return "redirect:" + request.getHeader("Referer");
	}
}
