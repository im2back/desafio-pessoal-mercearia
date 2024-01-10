package com.example.im2back.mercearia.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.im2back.mercearia.infra.aspect.clienteserviceaspect.ClienteEpathAspect;
import com.example.im2back.mercearia.infra.exceptions.ServiceExceptions;
import com.example.im2back.mercearia.infra.utils.CriadorPDF;
import com.example.im2back.mercearia.infra.utils.Util;
import com.example.im2back.mercearia.model.carrinho.DadosParaNotaDTO;
import com.example.im2back.mercearia.model.cliente.Cliente;
import com.example.im2back.mercearia.model.cliente.ClienteCadastroRequestDTO;
import com.example.im2back.mercearia.model.cliente.ClienteCadastroResponseDTO;
import com.example.im2back.mercearia.model.cliente.ClienteCompletoDTO;
import com.example.im2back.mercearia.model.cliente.ClienteListarTodosDTO;
import com.example.im2back.mercearia.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteEpathAspect clienteEpathAspect;

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private JavaMailSender javaMailSender;

	public String excluirCliente(String documento) throws IOException {
		repository.deleteByDocumento(documento);
		return "Cliente Deletado com sucesso, confira a nota backup";
	}

	public ClienteCadastroResponseDTO salvarCliente(ClienteCadastroRequestDTO clienteRequest) {
		repository.save(new Cliente(clienteRequest));
		return new ClienteCadastroResponseDTO(clienteRequest);
	}

	public Cliente findById(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new ServiceExceptions("Cliente de ID: " + id + " não foi encontrado na base de dados."));
	}

	public Cliente findByDocumento(String documento) {
		return repository.findByDocumento(documento);
	}

	public ClienteCompletoDTO localizarClientePorDocumento(String documento) {
		return Optional.ofNullable(repository.findByDocumento(documento)).map(ClienteCompletoDTO::new).orElseThrow(
				() -> new ServiceExceptions("O documento: '" + documento + "' não foi localizado na base de dados."));
	}

	public List<ClienteListarTodosDTO> listarTodosOsClientes() {

		return repository.findAll().stream().map(ClienteListarTodosDTO::new).collect(Collectors.toList());
	}

	public String gerarNotaDoClientePDF(String documento) throws IOException {
		new CriadorPDF(javaMailSender).gerarPDF(localizarEtratarListaDeProdutos(clienteEpathAspect.getCliente()),
				clienteEpathAspect.getCliente(), clienteEpathAspect.getPath());
		return "Nota Gerada com Sucesso";
	}

	public void deleteByDocumento(String documento) {
		repository.deleteByDocumento(documento);
	}

	private List<DadosParaNotaDTO> localizarEtratarListaDeProdutos(Cliente cliente) {
		var listaDeProdutosTriada = Util.triarCarrinho(cliente.getCarrinho());
		List<DadosParaNotaDTO> listaProdutosParaPdfDto = listaDeProdutosTriada.stream()
				.map(p -> new DadosParaNotaDTO(p.getName(), p.getPreco(), p.getMoment())).collect(Collectors.toList());

		return listaProdutosParaPdfDto;
	}

}
