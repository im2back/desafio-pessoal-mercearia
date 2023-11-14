package com.example.im2back.mercearia.model.cliente;

import jakarta.validation.constraints.NotBlank;

public record ClienteCadastroRequestDTO(
		
		@NotBlank
		String name,
		
		@NotBlank
		String documento,
		
		@NotBlank
		String rua,
		
		@NotBlank
		String numero
		
		) {

}
