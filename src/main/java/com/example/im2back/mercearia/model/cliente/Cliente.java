package com.example.im2back.mercearia.model.cliente;

import java.util.ArrayList;
import java.util.List;

import com.example.im2back.mercearia.model.carrinho.ProdutosComprados;
import com.example.im2back.mercearia.model.endereco.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Cliente")
@Table(name = "tb_cliente")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Cliente {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Column(name = "nome")
	private String name;
	
	private String documento;
	
	@Getter @Setter
	private String email;
	@Getter @Setter
	private String telefone;

	@Embedded
	private Endereco endereco = new Endereco();
	
	
	@OneToMany(mappedBy = "client")
	private List<ProdutosComprados> carrinho = new ArrayList<>();
	
	public Cliente(ClienteCadastroRequestDTO dto) {
		super();
		this.name = dto.name();
		this.documento = dto.documento();
		this.endereco.setNumero(dto.numero());
		this.endereco.setRua(dto.rua());
		this.email = dto.email();
		this.telefone = dto.telefone();
	}
	public Cliente(String name, String documento, String email, String telefone, Endereco endereco) {
		super();
		this.name = name;
		this.documento = documento;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	public Cliente(String name, String documento, Endereco endereco) {
		super();
		this.name = name;
		this.documento = documento;
		this.endereco = endereco;
	}
	
	
	public Double getTotalAtivoEinativo() {
		Double total = 0.0;
		for(ProdutosComprados x : this.carrinho) {
			total = total + x.getPreco();
		}
		return  total;
	}
	
	public Double getTotalInativo() {
		Double total = 0.0;
		for(ProdutosComprados x : this.carrinho) {
			if(x.isStatus() == false) {
				total = total + x.getPreco();
			}			
		}
		return  total;
	}
	
	public Double getTotalAtivo() {
		Double total = 0.0;
		for(ProdutosComprados x : this.carrinho) {
			if(x.isStatus() == true) {
				total = total + x.getPreco();
			}			
		}
		return  total;
	}



}
