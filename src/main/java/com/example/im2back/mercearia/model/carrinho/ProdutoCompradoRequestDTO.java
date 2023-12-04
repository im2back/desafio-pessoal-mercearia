package com.example.im2back.mercearia.model.carrinho;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoCompradoRequestDTO(
		
		@NotBlank
		String name,
		
		@NotNull
		Double preco,
		
		 @NotBlank
		 String documento,
		 
		 String obs
		) {

}
