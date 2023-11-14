package com.example.im2back.mercearia.model.cliente;

public record ClienteListarTodosDTO(
		String nome,
		String documento,
		Double total
		) {
	public ClienteListarTodosDTO(Cliente cliente) {
		this(cliente.getName(),cliente.getDocumento(),cliente.getTotal());
	}
	
}
