package com.example.im2back.mercearia.infra.aspect.produtoserviceaspect;

import java.io.IOException;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.im2back.mercearia.model.cliente.Cliente;
import com.example.im2back.mercearia.service.ClienteService;

@Aspect
@Component
public class GerarPdfPorDocumentoAspect {

	private final ClienteService clienteService;
	private Cliente cliente; 
	
	GerarPdfPorDocumentoAspect(ClienteService clienteService){
		this.clienteService = clienteService;
	}
	
	 @Before("execution(* com.example.im2back.mercearia.service.ProdutosCompradosService.zerarConta(..)) && args(documento)")
	    public void beforeSalvarCompra(String documento) throws IOException {
		 	cliente = clienteService.findByDocumento(documento);
			clienteService.gerarNotaDoClientePDF(cliente.getDocumento());       
	    }
	 public Cliente getCliente() {
	        return this.cliente;
	    }
}
