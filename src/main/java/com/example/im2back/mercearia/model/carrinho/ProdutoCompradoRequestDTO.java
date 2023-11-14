package com.example.im2back.mercearia.model.carrinho;

public record ProdutoCompradoRequestDTO(
		String name,
		Double preco,
		Long idCliente
		) {

}
