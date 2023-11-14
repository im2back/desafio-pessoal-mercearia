package com.example.im2back.mercearia.model.cliente;

public record ClienteCadastroResponseDTO(
		String name,
		
		String documento,
		
		String rua,
		
		String numero
		) {
	public ClienteCadastroResponseDTO(ClienteCadastroRequestDTO dto) {
		this (dto.name(), dto.documento(),dto.rua(),dto.numero());
	}
}
