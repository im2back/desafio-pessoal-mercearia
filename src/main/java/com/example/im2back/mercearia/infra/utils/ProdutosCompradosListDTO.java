package com.example.im2back.mercearia.infra.utils;

import java.time.LocalDateTime;

public record ProdutosCompradosListDTO(
		String name,
		Double price,
		LocalDateTime Data
	
		) {

}
