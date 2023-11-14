package com.example.im2back.mercearia.model.endereco;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;


@Embeddable
public class Endereco {
	
	
	@Getter @Setter
	private String rua;
	@Getter @Setter
	private String numero;
	

	
	

}
