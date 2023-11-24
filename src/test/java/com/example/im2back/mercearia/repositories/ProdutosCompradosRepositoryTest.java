package com.example.im2back.mercearia.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.example.im2back.mercearia.model.carrinho.ProdutoCompradoRequestDTO;
import com.example.im2back.mercearia.model.carrinho.ProdutosComprados;
import com.example.im2back.mercearia.model.cliente.Cliente;
import com.example.im2back.mercearia.model.endereco.Endereco;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class ProdutosCompradosRepositoryTest {
	@Autowired
	private ProdutosCompradosRepository  produtosCompradosRepository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	@DisplayName("Deveria Retornar uma lista vazia após a exclusão")
	void deletarHistoricoDeComprasPorIDCenario01() {

			var cliente = cadastrarCliente();
			cadastrarCarrinho(cliente);
			
			produtosCompradosRepository.deleteByClient_id(cliente.getId());		
			var list = produtosCompradosRepository.findAll();
			
			assertTrue(list.isEmpty(), "A lista de produtos comprados deveria estar vazia após a exclusão.");
	}
	
	
	private Cliente cadastrarCliente() {
		Endereco endereco = new Endereco();
		endereco.setNumero("06");
		endereco.setRua("Travessa E");
		var cliente = new Cliente("Jefferson","123456" ,endereco);
		em.persist(cliente);
			return cliente;
	}

	private void cadastrarCarrinho(Cliente cliente) {	
	ProdutoCompradoRequestDTO dto= new ProdutoCompradoRequestDTO("banana",(double) 20, "123456");	
		ProdutosComprados produtos = new ProdutosComprados(dto, cliente);
			em.persist(produtos);
	}
}
