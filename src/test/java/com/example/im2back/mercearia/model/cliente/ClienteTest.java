package com.example.im2back.mercearia.model.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;

import com.example.im2back.mercearia.model.carrinho.ProdutoCompradoRequestDTO;
import com.example.im2back.mercearia.model.carrinho.ProdutosComprados;
import com.example.im2back.mercearia.model.endereco.Endereco;



@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ClienteTest {

	
	@Test
	@DisplayName("Deveria devolver a somatoria total do carrinho do cliente")
	    void testGetTotal() {
	        // Criando instâncias necessárias
	        Endereco endereco = new Endereco();
	        endereco.setNumero("06");
	        endereco.setRua("Travessa E");

	        Cliente cliente = new Cliente("Jefferson", "123456", endereco);

	        ProdutoCompradoRequestDTO produto1 = new ProdutoCompradoRequestDTO("banana", 20.0, "123456","123");
	        ProdutoCompradoRequestDTO produto2 = new ProdutoCompradoRequestDTO("uva", 60.0, "123456","123");

	        ProdutosComprados produtos1 = new ProdutosComprados(produto1, cliente);
	        ProdutosComprados produtos2 = new ProdutosComprados(produto2, cliente);

	        // Adicionando produtos ao carrinho
	        cliente.getCarrinho().add(produtos1);
	        cliente.getCarrinho().add(produtos2);

	        // Verificando o cálculo do total
	        assertEquals(80.0, cliente.getTotal());
	    }
	
}
