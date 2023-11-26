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

		var tokenParametro = request.getParameter("token");
		var atributo = request.getAttribute("permissao");
		System.out.println(atributo);
		if (tokenParametro != null) {
			try {
				var subject = tokenService.getSubject(tokenParametro);
				var usuario = repository.findByLogin(subject);
				var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (JWTExceptions ex) {
				request.setAttribute("JWTMessage", ex.getMessage());
				request.getRequestDispatcher("/login").forward(request, response);
				return;
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		try {
			System.out.println("Entoru aki");
			filterChain.doFilter(request, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		

	}

}
