package com.example.im2back.mercearia.model.cliente;

import jakarta.validation.constraints.NotBlank;

public record DocumentoDTO(
		@NotBlank
		String documento
		) {

}
