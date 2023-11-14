package com.example.im2back.mercearia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.im2back.mercearia.model.carrinho.ProdutoCompradoRequestDTO;
import com.example.im2back.mercearia.service.ProdutosCompradosService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "produtos")
public class ProdutosCompradosController {
@Autowired
private ProdutosCompradosService service;


@GetMapping
public String cadastrarProduto(Model model){
		return "produto/Adicionar-Produto-No-Carrinho";	
}

@PostMapping("/cadastrar")
@Transactional
public String salvar(@Valid ProdutoCompradoRequestDTO dtoRequest){
	
	service.salvar(dtoRequest);
		return "produto/Adicionar-Produto-No-Carrinho";	
	}
}
