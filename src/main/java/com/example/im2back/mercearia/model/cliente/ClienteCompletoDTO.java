package com.example.im2back.mercearia.model.cliente;

import java.util.List;

import com.example.im2back.mercearia.model.carrinho.ProdutosComprados;

 
public record ClienteCompletoDTO(
		String name,
		String documento,
		String email,
		String telefone,
		String rua,
		String numero,
		Double total,
		
		List<ProdutosComprados> lista
		) {
	
	public ClienteCompletoDTO(Cliente cliente) {
		this(cliente.getName(),cliente.getDocumento(),cliente.getEmail(),cliente.getTelefone(),cliente.getEndereco().getRua(),cliente.getEndereco().getNumero(),cliente.getTotal(),cliente.getCarrinho());
	}


}
