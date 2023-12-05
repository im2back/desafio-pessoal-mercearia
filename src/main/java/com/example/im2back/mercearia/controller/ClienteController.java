package com.example.im2back.mercearia.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.im2back.mercearia.event.RecursoCriadoEvento;
import com.example.im2back.mercearia.model.cliente.ClienteCadastroRequestDTO;
import com.example.im2back.mercearia.model.cliente.DocumentoDTO;
import com.example.im2back.mercearia.service.ClienteService;
import com.example.im2back.mercearia.service.ProdutosCompradosService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "cliente")
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	@Autowired
	private ProdutosCompradosService produtosCompradosService; 

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/home")
	public String paginaInicial(Model model, HttpServletRequest request, HttpServletResponse response) {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
				return "cliente/home";
		}

	@PostMapping("/cadastrar")
	@Transactional
	public String salvar(@Valid ClienteCadastroRequestDTO clienteRequest, Model model, HttpServletRequest request) {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
			model.addAttribute("cliente", service.salvar(clienteRequest));
			   return "cliente/Response-Cliente-Cadastrado";
	}

	public @GetMapping("/cadastrar")
	String cadastrarCliente(Model model, HttpServletRequest request) {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));	
			return "cliente/Formulario-Cliente-Cadastro";
	}

	@GetMapping("/listar")
	public String listarTodos(Model model, HttpServletRequest request) {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
				model.addAttribute("lista",service.listarTodosOsClientes());		
					return "cliente/Buscar-Todos-Clientes";
	}

	@GetMapping("/buscar")
	public String buscarCliente(Model model, HttpServletRequest request) {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
			return "cliente/Formulario-Buscar-Cliente-Especifico";
	}

	@GetMapping("/buscarCliente")
	public String buscarClientePorDocumento(@Valid DocumentoDTO dto, Model model, HttpServletRequest request) {	
		String documento = request.getParameter("documento");
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
				model.addAttribute("cliente", service.localizarClientePorDocumento(documento));
					return "cliente/Response-Cliente-Completo";
				}
	
	@GetMapping("/gerar")
	public String gerarNota(Model model, HttpServletRequest request) throws IOException {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
				model.addAttribute("NotaMessage",service.gerarNotaClientePDF(request.getParameter("documento")));
						return "forward:/cliente/"+request.getParameter("redirect");
	}	
	
	@Transactional
	@PostMapping("/delete")
	public String excluirCliente(Model model, HttpServletRequest request) throws IOException {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
				model.addAttribute("NotaMessage", produtosCompradosService.excluirCliente(request.getParameter("documento")));
						return "cliente/home";
	}
}
