package com.example.im2back.mercearia.model.carrinho;

import java.time.LocalDateTime;

public record DadosParaNotaDTO(
		String name,
		Double price,
		LocalDateTime Data
	
		) {

}
