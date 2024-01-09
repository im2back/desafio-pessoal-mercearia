package com.example.im2back.mercearia.infra.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.im2back.mercearia.infra.exceptions.ServiceExceptions;
import com.example.im2back.mercearia.model.cliente.ClienteCadastroRequestDTO;
import com.example.im2back.mercearia.service.ClienteService;

@Aspect
@Component
public class ValidarCadastroClienteAspect {

	private final ClienteService clienteService;


	public ValidarCadastroClienteAspect(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Before("execution(* com.example.im2back.mercearia.service.ClienteService.salvarCliente(..)) && args(clienteRequest)")
	public void beforeSalvarCompra(ClienteCadastroRequestDTO clienteRequest) {
		
		var clienteValidar = clienteService.findByDocumento(clienteRequest.documento());
		
		if(clienteValidar != null) {	
			throw new ServiceExceptions("O documento : '" + clienteRequest.documento() + " ja esta cadastrado para um cliente");	
		} 
	}
	
}
