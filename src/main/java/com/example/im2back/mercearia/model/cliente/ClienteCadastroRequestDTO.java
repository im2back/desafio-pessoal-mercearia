package com.example.im2back.mercearia.model.cliente;

import jakarta.validation.constraints.NotBlank;

public record ClienteCadastroRequestDTO(
		
		@NotBlank
		String name,
		
		@NotBlank
		String documento,
		
		String email,
		
		String telefone,
		
	
		String rua,
		
		
		String numero
			
		
		
		) {

}
