package com.example.im2back.mercearia.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.im2back.mercearia.infra.aspect.produtoserviceaspect.GerarPdfPorDocumentoAspect;
import com.example.im2back.mercearia.infra.aspect.produtoserviceaspect.PesquisarClienteIdAspect;
import com.example.im2back.mercearia.infra.utils.DadosGraficoDto;
import com.example.im2back.mercearia.infra.utils.Util;
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
	private PesquisarClienteIdAspect  pesquisarClienteIdAspect;	
	@Autowired
	private GerarPdfPorDocumentoAspect gerarPpfPorDocumentoAspect;
	
	public ProdutoCompradoResponseDTO salvarCompra(ProdutoCompradoRequestDTO dto) {				
			repository.save(new ProdutosComprados(dto, pesquisarClienteIdAspect.getCliente()));		
				return new ProdutoCompradoResponseDTO(dto, pesquisarClienteIdAspect.getCliente().getName());		
	}
	
	public List<ProdutosComprados> listar() {
		return repository.findAll();
	}

	public List<ProdutosComprados> findByStatusTrue() {
		return repository.findByStatusTrue();
	}

	public void zerarConta(String documento) throws IOException {
		exclusaoLogicaDeProdutos(gerarPpfPorDocumentoAspect.getCliente());
	}

	public ValoresDto montarEstatisticas() {
		Double total = Util.verificarValorNulo(repository.valorTotal());
		Double totalDoMesAnterior = Util.verificarValorNulo(repository.valorTotalMesAnterior());
		Double totalDoDia = Util.verificarValorNulo(repository.valorTotalDoDia());
		Double totalParcial = Util.verificarValorNulo(repository.valorVendidoDoInicioDoMesAtéAgora());
		
		return new ValoresDto(total, totalDoDia, totalDoMesAnterior, totalParcial);
	}

	public List<DadosGraficoDto> GraficoDto() {		
		List<DadosGraficoDto> listaDadosGraficoDto = repository.obterSomaPrecoPorDataUltimos7Dias();			
			listaDadosGraficoDto.forEach(dado -> { dado.setData(Util.formatarDataBrasileira(dado.getDataBruta()));});		
				return listaDadosGraficoDto;
	}

	public void exclusaoIndividualDeProduto(String idProduto) {		
		Long idProdutoConvert = Long.parseLong(idProduto);
			repository.deleteById(idProdutoConvert);
	}
	
	public void exclusaoLogicaDeProdutos(Cliente cliente) {
		List<ProdutosComprados> listaDeProdutos = cliente.getCarrinho();
			listaDeProdutos.forEach(p -> p.mudarStatus());
				repository.saveAll(listaDeProdutos);
	}
	
	public void exclusaoPorIdDoCliente(Long id) {
		repository.deleteByClient_id(id);
	}
	
	
}
