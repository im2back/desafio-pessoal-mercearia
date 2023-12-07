package com.example.im2back.mercearia.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.im2back.mercearia.infra.exceptions.ServiceExceptions;
import com.example.im2back.mercearia.infra.utils.CriadorPDF;
import com.example.im2back.mercearia.infra.utils.ProdutosCompradosListDTO;
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

	@Autowired
	private JavaMailSender javaMailSender;

	public ClienteCadastroResponseDTO salvar(ClienteCadastroRequestDTO clienteRequest) {
		Cliente cliente = new Cliente(clienteRequest);
		try {
			repository.save(cliente);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceExceptions(
					"O documento : '" + clienteRequest.documento() + " ja esta cadastrado para um cliente");
		}
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

	public String gerarNotaClientePDF(String documento) throws IOException {
		Cliente cliente = repository.findByDocumento(documento);
		List<ProdutosCompradosListDTO> listDTO = cliente.getCarrinho().stream()
				.map(p -> new ProdutosCompradosListDTO(p.getName(), p.getPreco(), p.getMoment()))
				.collect(Collectors.toList());

		String path = Paths.get("C:\\Users\\jeffe\\OneDrive\\Área de Trabalho\\Notas Detalhadas",
				cliente.getName() + "_nota_fiscal_" + ".pdf").toString();

		new CriadorPDF(javaMailSender).gerarPDF(listDTO, cliente.getName(), path, cliente.getTotal(),
				cliente.getDocumento(), cliente.getEmail());

		return "Nota Gerada com Sucesso";
	}

	public void deleteByDocumento(String documento) {
		repository.deleteByDocumento(documento);
	}


}
