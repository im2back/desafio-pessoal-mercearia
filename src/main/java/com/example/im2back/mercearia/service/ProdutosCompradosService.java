package com.example.im2back.mercearia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.im2back.mercearia.infra.exceptions.ServiceExceptions;
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

		try {
			Cliente cliente = clienteService.findByDocumento(dto.documento());
			ProdutosComprados produto = new ProdutosComprados(dto, cliente);
			repository.save(produto);
			ProdutoCompradoResponseDTO response = new ProdutoCompradoResponseDTO(dto, cliente.getName());
				return response;
		} catch (NullPointerException e) {
			throw new ServiceExceptions("O documento : '" + dto.documento() + "' não foi localizado na base de dados.");
		}
	}

	public List<ProdutosComprados> listar() {
		return repository.findAll();
	}

	public void zerarConta(String documento) {
		Cliente cliente = clienteService.findByDocumento(documento);
		repository.deleteByClient_id(cliente.getId());
		clienteService.geraradorNotaClientePDF(cliente.getDocumento());
	
	}
	
	public String excluirCliente(String documento) {
		zerarConta(documento);
			clienteService.deleteByDocumento(documento);
				return "Cliente Deletado com sucesso, confira a nota backup";
	}

}
