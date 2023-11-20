package com.example.im2back.mercearia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.im2back.mercearia.infra.exceptions.ServiceExceptions;
import com.example.im2back.mercearia.infra.utils.CriadorPDF;
import com.example.im2back.mercearia.infra.utils.ProdutosCompradosListDTO;
import com.example.im2back.mercearia.model.carrinho.ProdutosComprados;
import com.example.im2back.mercearia.model.cliente.Cliente;
import com.example.im2back.mercearia.model.cliente.ClienteCadastroRequestDTO;
import com.example.im2back.mercearia.model.cliente.ClienteCadastroResponseDTO;
import com.example.im2back.mercearia.model.cliente.ClienteCompletoDTO;
import com.example.im2back.mercearia.model.cliente.ClienteListarTodosDTO;
import com.example.im2back.mercearia.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public ClienteCadastroResponseDTO salvar(ClienteCadastroRequestDTO clienteRequest) {
		Cliente cliente = new Cliente(clienteRequest);
		repository.save(cliente);
		ClienteCadastroResponseDTO response = new ClienteCadastroResponseDTO(clienteRequest);
		return response;
	}

	public Cliente findById(Long id) {
		try {
			Optional<Cliente> cliente = repository.findById(id);
			return cliente.get();
		} catch (NoSuchElementException e) {
			throw new ServiceExceptions("Cliente de ID : " + id + " não  foi encontrado na base de dados.");
		}
	}

	public Cliente findByDocumento(String documento) {
		return repository.findByDocumento(documento);

	}

	public ClienteCompletoDTO localizarClientePorDocumento(String documento) {
		try {
			Cliente cliente = repository.findByDocumento(documento);
			ClienteCompletoDTO dto = new ClienteCompletoDTO(cliente);		
			
			return dto;
		} catch (NullPointerException e) {
			throw new ServiceExceptions("O documento : '" + documento + "' não foi localizado na base de dados.");
		}

	}

	public List<ClienteListarTodosDTO> listarTodosOsClientes() {
		List<Cliente> listaClientes = repository.findAll();
		List<ClienteListarTodosDTO> response = listaClientes.stream().map(ClienteListarTodosDTO::new)
				.collect(Collectors.toList());
		
		
		return response;
	}
	
	public void geraradorNotaClientePDF(String documento) {
		
		Cliente cliente = repository.findByDocumento(documento); // localiza o cliente
		var carrinho = cliente.getCarrinho(); // pega o carrinho com todas as compras feitas pelo cliente
		
		// apartir do carrinho, eu instancio uma lista do meu DTO que contem as informações que serão impressas.
		List<ProdutosCompradosListDTO> listDTO = new ArrayList<>(); 
		for (ProdutosComprados p : carrinho) {
			var novo = new ProdutosCompradosListDTO(p.getName(), p.getPreco(), p.getMoment()); 
			listDTO.add(novo);
		}
		
		// caminho onde o arquivo será salvo + nome dinamico 
		String path = "C:\\Users\\jeffe\\OneDrive\\Área de Trabalho\\Notas Detalhadas\\"+cliente.getName()+"_nota_fiscal_"+ ".pdf";
		
		//método para gerar o pdf
		CriadorPDF criador = new CriadorPDF();
		criador.gerarPDF(listDTO, cliente.getName(), path, cliente.getTotal(),cliente.getDocumento());
	}

}
