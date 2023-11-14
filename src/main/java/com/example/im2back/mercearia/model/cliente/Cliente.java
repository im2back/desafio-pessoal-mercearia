package com.example.im2back.mercearia.model.cliente;

import java.util.ArrayList;
import java.util.List;

import com.example.im2back.mercearia.model.carrinho.ProdutosComprados;
import com.example.im2back.mercearia.model.endereco.Endereco;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "tb_cliente")
public class Cliente {

	@Getter
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String documento;
	
	@Getter @Setter
	@Embedded
	private Endereco endereco = new Endereco();
	
	@Getter
	@OneToMany(mappedBy = "client")
	private List<ProdutosComprados> carrinho = new ArrayList<>();
	
	public Cliente(ClienteCadastroRequestDTO dto) {
		super();
		this.name = dto.name();
		this.documento = dto.documento();
		this.endereco.setNumero(dto.numero());
		this.endereco.setRua(dto.rua());
	}
	
	public Cliente(String name, String documento, Endereco endereco) {
		super();
		this.name = name;
		this.documento = documento;
		this.endereco = endereco;
	}
	
	public Double getTotal() {
		Double total = 0.0;
		for(ProdutosComprados x : this.carrinho) {
			total = total + x.getPreco();
		}
		return  total;
	}
}
