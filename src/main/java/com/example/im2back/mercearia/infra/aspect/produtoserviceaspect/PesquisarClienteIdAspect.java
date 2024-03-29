package com.example.im2back.mercearia.infra.aspect.produtoserviceaspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.im2back.mercearia.infra.exceptions.ServiceExceptions;
import com.example.im2back.mercearia.model.carrinho.ProdutoCompradoRequestDTO;
import com.example.im2back.mercearia.model.cliente.Cliente;
import com.example.im2back.mercearia.service.ClienteService;

@Aspect
@Component
public class PesquisarClienteIdAspect {

	 private final ClienteService clienteService;
	 private Cliente cliente; 
	 
	 public PesquisarClienteIdAspect( ClienteService clienteService) {        
	        this.clienteService = clienteService;
	    }
	 
	 @Before("execution(* com.example.im2back.mercearia.service.ProdutosCompradosService.salvarCompra(..)) && args(dto)")
	    public void beforeSalvarCompra(ProdutoCompradoRequestDTO dto) {
		 	        	        			
	        	cliente = clienteService.findByDocumento(dto.documento());				
	        	if(cliente ==null) {
	       				throw new ServiceExceptions("O documento : '" + dto.documento() + "' não foi localizado na base de dados.");
	        	}
	    }
	 
	 public Cliente getCliente() {
	        return cliente;
	    }
	  
}
