package com.example.im2back.mercearia.model.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
	@NotBlank
	String login,
	@NotBlank
	String senha
		) {

}
