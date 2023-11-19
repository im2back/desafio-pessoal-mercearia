package com.example.im2back.mercearia.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.im2back.mercearia.infra.exceptions.JWTExceptions;
import com.example.im2back.mercearia.repositories.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	@Autowired
	private TokenService tokenService;

	@Autowired
	UsuarioRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// var tokenJWT = recuperarToken(request);

		var tokenParametro = request.getParameter("token");

		if (tokenParametro != null) {
			try {
				var subject = tokenService.getSubject(tokenParametro);
					var usuario = repository.findByLogin(subject);
						var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			} catch (JWTExceptions ex) {
				// Redirecionamento interno para o manipulador de exceções do filtro
				request.setAttribute("JWTMessage", ex.getMessage());
				request.getRequestDispatcher("/login").forward(request, response);
				return;
			}
		}

		filterChain.doFilter(request, response);

		/*
		 * /* Método usado para token sendo enviado por cabeçalho if (tokenJWT != null)
		 * {
		 * 
		 * 
		 * var subject = tokenService.getSubject(tokenJWT);
		 * 
		 * var usuario = repository.findByLogin(subject);
		 * 
		 * var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
		 * usuario.getAuthorities());
		 * 
		 * SecurityContextHolder.getContext().setAuthentication(authentication);
		 * 
		 * }
		 */

	}

	/*
	 * Método usado para token sendo enviado por cabeçalho private String
	 * recuperarToken(HttpServletRequest request) {
	 * 
	 * var authorizationHeader = request.getHeader("Authorization"); if
	 * (authorizationHeader != null) { return authorizationHeader.replace("Bearer ",
	 * ""); } return null; }
	 */

}
