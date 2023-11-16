package com.example.im2back.mercearia.model.carrinho;

import java.time.Instant;

import com.example.im2back.mercearia.model.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ProdutosComprados")
@Table(name = "produtosComprados")
public class ProdutosComprados {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String name;
	
	private Double preco;
	
	@ManyToOne @JsonIgnore
	@JoinColumn(name = "client_id")
	private Cliente client;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
	private Instant moment  =Instant.now();


	
	public ProdutosComprados (ProdutoCompradoRequestDTO dto, Cliente client) {
		
		this.name = dto.name();
		this.preco = dto.preco();
		this.client = client;
		
	}


	
	
}
