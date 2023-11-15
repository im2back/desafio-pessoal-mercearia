package com.example.im2back.mercearia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.im2back.mercearia.model.carrinho.ProdutoCompradoRequestDTO;
import com.example.im2back.mercearia.model.carrinho.ProdutoCompradoResponseDTO;
import com.example.im2back.mercearia.model.carrinho.ProdutosComprados;
import com.example.im2back.mercearia.model.cliente.Cliente;
import com.example.im2back.mercearia.repositories.ProdutosCompradosRepository;

@Service
public class ProdutosCompradosService {

	@Autowired
	private ProdutosCompradosRepository repository;

	@Autowired
	private ClienteService clienteService;

	public ProdutoCompradoResponseDTO salvar(ProdutoCompradoRequestDTO dto) {

		Cliente cliente = clienteService.findById(dto.idCliente());
			ProdutosComprados produto = new ProdutosComprados(dto, cliente);
				repository.save(produto);
					ProdutoCompradoResponseDTO response = new ProdutoCompradoResponseDTO(dto, cliente.getName());
						return response;
	}

	public List<ProdutosComprados> listar() {
		return repository.findAll();
	}

}
