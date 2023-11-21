package com.example.im2back.mercearia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.im2back.mercearia.event.RecursoCriadoEvento;
import com.example.im2back.mercearia.model.cliente.ClienteCadastroRequestDTO;
import com.example.im2back.mercearia.model.cliente.ClienteCompletoDTO;
import com.example.im2back.mercearia.model.cliente.DocumentoDTO;
import com.example.im2back.mercearia.service.ClienteService;

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
		  var response = service.salvar(clienteRequest);
			model.addAttribute("cliente", response);
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
			var lista = service.listarTodosOsClientes();
				model.addAttribute("lista",lista);		
					return "cliente/Buscar-Todos-Clientes";
	}

	@GetMapping("/buscar")
	public String buscarCliente(Model model, HttpServletRequest request) {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
			return "cliente/Formulario-Buscar-Cliente-Especifico";
	}

	@GetMapping("/buscarCliente")
	public String buscarClientePorDocumento( @Valid DocumentoDTO dto, Model model, HttpServletRequest request) {
		String documento = request.getParameter("documento");
		if(documento != null) {
			publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
				DocumentoDTO dtoParam = new DocumentoDTO(documento);
					ClienteCompletoDTO clienteDTO = service.localizarClientePorDocumento(dtoParam.documento());
						model.addAttribute("cliente", clienteDTO);
							return "cliente/Response-Cliente-Completo";
		}
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
			var clienteDTO = service.localizarClientePorDocumento(dto.documento());
				model.addAttribute("cliente", clienteDTO);
					return "cliente/Response-Cliente-Completo";
			}
	
	@GetMapping("/gerar")
	public String gerarNota(Model model, HttpServletRequest request) {
		publisher.publishEvent(new RecursoCriadoEvento(this,model,request));
			String documento = request.getParameter("documento");
				model.addAttribute("NotaMessage",service.geraradorNotaClientePDF(documento));
					String url = request.getParameter("redirect");
						return "forward:/cliente/"+url;
	}	

}
