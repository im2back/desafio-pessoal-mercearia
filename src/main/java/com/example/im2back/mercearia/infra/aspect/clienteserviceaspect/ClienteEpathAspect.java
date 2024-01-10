package com.example.im2back.mercearia.infra.aspect.clienteserviceaspect;

import java.io.IOException;
import java.nio.file.Paths;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.im2back.mercearia.model.cliente.Cliente;
import com.example.im2back.mercearia.repositories.ClienteRepository;

@Aspect
@Component
public class ClienteEpathAspect {
	@Autowired
	private ClienteRepository service;
	
	private String path;	
	private Cliente cliente;
	
	@Before("execution(* com.example.im2back.mercearia.service.ClienteService.gerarNotaDoClientePDF(..)) && args(documento)")
    public  void localizarClienteEGerarPath(String documento) throws IOException {
		cliente = service.findByDocumento(documento);

        // Obtém o diretório do usuário
        String userHome = System.getProperty("user.home");

        // Obtém o diretório da área de trabalho
        String desktopDirectory = Paths.get(userHome).toString();

        path = Paths.get(desktopDirectory, cliente.getName() + "_nota_fiscal_" + ".pdf").toString();
 
    }
	
	public String getPath() {
		return path;
	}	

	public Cliente getCliente() {
		return cliente;
	}

	

}
