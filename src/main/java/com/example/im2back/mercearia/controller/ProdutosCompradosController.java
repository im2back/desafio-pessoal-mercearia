package com.example.im2back.mercearia.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.im2back.mercearia.event.RecursoCriadoEvento;
import com.example.im2back.mercearia.infra.utils.DadosGraficoDto;
import com.example.im2back.mercearia.model.carrinho.ProdutoCompradoRequestDTO;
import com.example.im2back.mercearia.model.carrinho.ValoresDto;
import com.example.im2back.mercearia.service.ProdutosCompradosService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	public String salvar(@Valid ProdutoCompradoRequestDTO dtoRequest, HttpServletRequest request, Model model,RedirectAttributes redirectAttributes) {
			service.salvarCompra(dtoRequest);
				redirectAttributes.addFlashAttribute("compra","Compra realizada com sucesso!");
					return "redirect:" + request.getHeader("Referer");
	}

	@Transactional
	@PostMapping("/delete")
	public String zerarRegistros(Model model, HttpServletRequest request,HttpServletRequest response) throws IOException {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
				service.zerarConta(request.getParameter("documento"));
						return "redirect:" + request.getHeader("Referer");
	}
	
	@Transactional
	@PostMapping("/deletar")
	public String deletarProduto(Model model, HttpServletRequest request,HttpServletRequest response) throws IOException {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
				service.exclusaoIndividualDeProduto(request.getParameter("idproduto"));	
				return "redirect:" + request.getHeader("Referer");
	}
	
	@GetMapping("/estatisticas")
	public String estatisticas(Model model, HttpServletRequest request, HttpServletResponse response) {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
			ValoresDto estatisticas = service.montarEstatisticas();
			List<DadosGraficoDto> grafico = service.GraficoDto();
				model.addAttribute("listaGraficoDto",grafico);
				model.addAttribute("valores",estatisticas);
					return "cliente/estatisticas";
		}
}
