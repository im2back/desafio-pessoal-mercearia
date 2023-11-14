package com.example.im2back.mercearia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.im2back.mercearia.model.cliente.ClienteCadastroRequestDTO;
import com.example.im2back.mercearia.model.cliente.IdDTO;
import com.example.im2back.mercearia.service.ClienteService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "cliente")
public class ClienteController {
	@Autowired
	private ClienteService service;

	@GetMapping("/home")
	public String paginaInicial(Model model) {
		return "cliente/home";
	}

	@PostMapping("/cadastrar")
	@Transactional
	String salvar(@Valid ClienteCadastroRequestDTO clienteRequest, Model model) {
		var response = service.salvar(clienteRequest);
		model.addAttribute("cliente", response);
		return "cliente/Response-Cliente-Cadastrado";
	}

	@GetMapping("/cadastrar")
	String cadastrarCliente(Model model) {
		return "cliente/Formulario-Cliente-Cadastro";
	}

	@GetMapping("/listar")
	String listarTodos(Model model) {
		var response = service.listarTodosOsClientes();
		model.addAttribute("lista", response);
		return "cliente/Buscar-Todos-Clientes";
	}

	@GetMapping("/buscar")
	String buscarCliente(Model model) {
		return "cliente/Formulario-Buscar-Cliente-Especifico";
	}

	@GetMapping("/buscarCliente")
	String buscarClientePorID(@Valid IdDTO dto, Model model) {

			var clienteDTO = service.localizarClientePorID2(dto.id());
			model.addAttribute("cliente", clienteDTO);
			System.out.println("request");
			return "cliente/Response-Cliente-Completo";
		
	}

}
