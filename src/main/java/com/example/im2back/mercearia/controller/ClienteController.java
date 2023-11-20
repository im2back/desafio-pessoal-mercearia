package com.example.im2back.mercearia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.im2back.mercearia.model.cliente.ClienteCadastroRequestDTO;
import com.example.im2back.mercearia.model.cliente.DocumentoDTO;
import com.example.im2back.mercearia.service.ClienteService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "cliente")
public class ClienteController {
	@Autowired
	private ClienteService service;

	@GetMapping("/home")
	public String paginaInicial(Model model, HttpServletRequest request) {
		var token = request.getParameter("token");
		model.addAttribute("token",token);
				return "cliente/home";
	}

	@PostMapping("/cadastrar")
	@Transactional
	public String salvar(@Valid ClienteCadastroRequestDTO clienteRequest, Model model, HttpServletRequest request) {
		var response = service.salvar(clienteRequest);
		model.addAttribute("cliente", response);
		var token = request.getParameter("token");
		model.addAttribute("token",token);
		
		return "cliente/Response-Cliente-Cadastrado";
	}

	public @GetMapping("/cadastrar")
	String cadastrarCliente(Model model, HttpServletRequest request) {
		var token = request.getParameter("token");
		model.addAttribute("token",token);
		
		return "cliente/Formulario-Cliente-Cadastro";
	}

	@GetMapping("/listar")
	public String listarTodos(Model model, HttpServletRequest request) {
		var token = request.getParameter("token");
		model.addAttribute("token",token);
	
		var lista = service.listarTodosOsClientes();
		model.addAttribute("lista",lista);
					
		return "cliente/Buscar-Todos-Clientes";
	}

	@GetMapping("/buscar")
	public String buscarCliente(Model model, HttpServletRequest request) {
		var token = request.getParameter("token");
		model.addAttribute("token",token);
		return "cliente/Formulario-Buscar-Cliente-Especifico";
	}

	@GetMapping("/buscarCliente")
	public String buscarClientePorDocumento( @Valid DocumentoDTO dto, Model model, 
			HttpServletRequest request) {
			var documento = request.getParameter("documento");
		if(documento != null) {
			var token = request.getParameter("token");
			model.addAttribute("token",token);
			 DocumentoDTO dtoParam = new DocumentoDTO(documento);
			 var clienteDTO = service.localizarClientePorDocumento(dtoParam.documento());
				model.addAttribute("cliente", clienteDTO);
					return "cliente/Response-Cliente-Completo";
		}
		var token = request.getParameter("token");
		model.addAttribute("token",token);
			var clienteDTO = service.localizarClientePorDocumento(dto.documento());
				model.addAttribute("cliente", clienteDTO);
					return "cliente/Response-Cliente-Completo";
		
	}
	
	@GetMapping("/gerar")
	public String gerarNota(Model model, HttpServletRequest request) {
		
		var token = request.getParameter("token");
		model.addAttribute("token",token);
		model.addAttribute("NotaMessage","Nota Gerada com Sucesso");
		
		String documento = request.getParameter("documento");
		service.geraradorNotaClientePDF(documento);
		
	    return "forward:/cliente/listar";
	}
	
	@GetMapping("/gerar2")
	public String gerarNotaClienteDetalhado(Model model, HttpServletRequest request) {
		
		var token = request.getParameter("token");
		model.addAttribute("token",token);
		model.addAttribute("NotaMessage","Nota Gerada com Sucesso");
		
		String documento = request.getParameter("documento");
		service.geraradorNotaClientePDF(documento);
		
	    return "forward:/cliente/buscarCliente";
	}

}
