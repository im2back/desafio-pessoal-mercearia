package com.example.im2back.mercearia.repositories;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.example.im2back.mercearia.model.cliente.Cliente;
import com.example.im2back.mercearia.model.endereco.Endereco;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class ClienteRepositoryTest {
	
	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private ClienteRepository clienteRepository; 
	
	@Test
	@DisplayName("Deveria Retornar nulo ao buscar o cliente deletado")
	void deleteByDocumento() {
		Cliente cliente = cadastrarCliente();
		
		clienteRepository.deleteByDocumento(cliente.getDocumento());
		var response = clienteRepository.findByDocumento(cliente.getDocumento());

		assertNull(response);	
	}
	
	private Cliente cadastrarCliente() {
		Endereco endereco = new Endereco();
		endereco.setNumero("06");
		endereco.setRua("Travessa E");
		var cliente = new Cliente("Jefferson","123456" ,endereco);
		em.persist(cliente);
			return cliente;
	}
	
}
