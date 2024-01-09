package com.example.im2back.mercearia.infra.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.im2back.mercearia.service.ClienteService;
import com.example.im2back.mercearia.service.ProdutosCompradosService;

@Aspect
@Component
public class ExcluirClienteAspect {

	   private final ProdutosCompradosService produtosCompradosService;
	   private final ClienteService clienteService;
	 
	    public ExcluirClienteAspect(ProdutosCompradosService produtosCompradosService, ClienteService clienteService) {
	        this.produtosCompradosService = produtosCompradosService;
	        this.clienteService = clienteService;
	    }

	    @Before("execution(* com.example.im2back.mercearia.service.ClienteService.excluirCliente(..)) && args(documento)")
	    public void afterExcluirCliente(String documento) {
	        produtosCompradosService.exclusaoPorIdDoCliente(clienteService.findByDocumento(documento).getId());
	    }

	   
	
}
