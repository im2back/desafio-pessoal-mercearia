package com.example.im2back.mercearia.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.im2back.mercearia.infra.exceptions.ServiceExceptions;
import com.example.im2back.mercearia.infra.utils.DadosGraficoDto;
import com.example.im2back.mercearia.model.carrinho.ProdutoCompradoRequestDTO;
import com.example.im2back.mercearia.model.carrinho.ProdutoCompradoResponseDTO;
import com.example.im2back.mercearia.model.carrinho.ProdutosComprados;
import com.example.im2back.mercearia.model.carrinho.ValoresDto;
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

	public void zerarConta(String documento) throws IOException {
		Cliente cliente = clienteService.findByDocumento(documento);
		clienteService.gerarNotaClientePDF(cliente.getDocumento());
		repository.deleteByClient_id(cliente.getId());
	}
	
	public void zerarContaSemNota(String documento) throws IOException {
		Cliente cliente = clienteService.findByDocumento(documento);
		repository.deleteByClient_id(cliente.getId());
	}
	
	public String excluirCliente(String documento) throws IOException {
		zerarContaSemNota(documento);
			clienteService.deleteByDocumento(documento);
				return "Cliente Deletado com sucesso, confira a nota backup";
	}

	public ValoresDto montarEstatisticas() {
		Double total = repository.valorTotal();	
		Double totalDoMesAnterior = repository.valorTotalMesAnterior();	
		
		Double totalDoDia = 0.00;
		if(repository.valorTotalDoDia() == null) {
			totalDoDia = 0.00;
		} else {
		totalDoDia =  repository.valorTotalDoDia();
		}
		
		
		Double totalParcial = 0.00;			
		if(repository.valorTotalDoDia() == null)  {
		 totalParcial = repository.valorVendidoDoInicioDoMesAtéAgora();
		}
		if (repository.valorTotalDoDia() != null) {
		 totalParcial = repository.valorVendidoDoInicioDoMesAtéAgora() + repository.valorTotalDoDia();
		}		
		
		return new ValoresDto(total, totalDoDia, totalDoMesAnterior, totalParcial);
	}
	
	public List<DadosGraficoDto> GraficoDto() {
		SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
		
		var listDadosGraficoDto = repository.obterSomaPrecoPorDataUltimos7Dias();
		
		for (DadosGraficoDto dado: listDadosGraficoDto) {
		         dado.setData(formatoBrasileiro.format(dado.getDataBruta()));
		}		
		return listDadosGraficoDto;
	}

	public void excluirProduto(String idProduto) {
		Long idProdutoConvert = Long.parseLong(idProduto);
		
		repository.deleteById(idProdutoConvert);
		
	}

}
