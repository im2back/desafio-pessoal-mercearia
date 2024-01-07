package com.example.im2back.mercearia.model.cliente;

import java.util.List;
import java.util.stream.Collectors;

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
		this(cliente.getName(),cliente.getDocumento(),cliente.getEmail(),cliente.getTelefone(),cliente.getEndereco().getRua(),
				cliente.getEndereco().getNumero(),cliente.getTotalAtivo(),triarCarrinho(cliente.getCarrinho()));
	}

	private static List<ProdutosComprados> triarCarrinho(List<ProdutosComprados> carrinho){	
		    return carrinho.stream()
		            .filter(p -> p.isStatus())
		            .collect(Collectors.toList());
			
	}
}
