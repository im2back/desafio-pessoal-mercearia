package com.example.im2back.mercearia.infra.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "tb_blacklist")
@Table(name = "tb_blacklist")
@AllArgsConstructor
@NoArgsConstructor
public class BlackListJWT {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "token")
	private String token;

	public BlackListJWT(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}
	
	
}
