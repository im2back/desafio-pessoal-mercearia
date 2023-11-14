package com.example.im2back.mercearia.model.cliente;

import jakarta.validation.constraints.NotNull;

public record IdDTO(
		@NotNull
		Long id
		) {

}
