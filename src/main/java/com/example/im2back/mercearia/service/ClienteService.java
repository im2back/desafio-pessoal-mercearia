package com.example.im2back.mercearia.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.im2back.mercearia.infra.exceptions.ServiceExceptions;
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

}
