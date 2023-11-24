package com.example.im2back.mercearia.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureJsonTesters
class ClienteControllerTest {

	@Autowired
	private MockMvc mvc;


	@Test
	@DisplayName("Deveria devolver o status FOUND ao tentar realizar um cadastro incorreto")
	@WithMockUser
	void salvarCenario01() throws Exception {

		var response = mvc.perform(post("/cliente/cadastrar")).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.MOVED_PERMANENTLY.value());
	}

	@Test
	@DisplayName("Deveria devolver status ok")
	@WithMockUser
	void salvarCenario02() throws Exception {
	    mvc.perform(post("/cliente/cadastrar")
	            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .param("name", "jeff")
	            .param("documento", "123213")
	            .param("rua", "travessa")
	            .param("numero", "06"))
	            .andExpect(status().isOk());
	}


}
