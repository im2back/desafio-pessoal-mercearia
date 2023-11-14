package com.example.im2back.mercearia.model.carrinho;

public record ProdutoCompradoResponseDTO(
		String produto,
		Double preco,
		String nomeComprador
		
		) {
	public ProdutoCompradoResponseDTO(ProdutoCompradoRequestDTO dados, String nomeComprador) {
		this(dados.name(),dados.preco(),nomeComprador);
	}

}
