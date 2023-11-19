package com.example.im2back.mercearia.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.im2back.mercearia.infra.exceptions.JWTExceptions;
import com.example.im2back.mercearia.infra.exceptions.ServiceExceptions;
import com.example.im2back.mercearia.model.usuario.Usuario;

@Service
public class TokenService {

	@Value("{api.security.token.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("API Caderneta Digital").withSubject(usuario.getLogin())
					.withExpiresAt(dataExpiracao()).sign(algoritmo);
		} catch (JWTCreationException exception) {
			throw new ServiceExceptions("Erro ao gerrar token jwt" + exception);
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusMinutes(1).toInstant(ZoneOffset.of("-03:00"));
		// return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

	public String getSubject(String tokenJWT) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.require(algoritmo).withIssuer("API Caderneta Digital").build().verify(tokenJWT).getSubject();
		}

		catch (JWTVerificationException ex) {
			throw new JWTExceptions("Token JWT inválido ou expirado!");
		}

	}
}
