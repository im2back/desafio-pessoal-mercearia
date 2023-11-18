package com.example.im2back.mercearia.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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
		
			var x = request.getParameter("token");
		   // var tokenJWT = recuperarToken(request); 
		  
		
		if(x != null ) {
			System.out.println("PARAMETRO");
			var subject = tokenService.getSubject(x); 

			var usuario = repository.findByLogin(subject);   

			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); 

			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		
		/*
		System.out.println("token do filtro: "+tokenJWT);
		
		if (tokenJWT != null) {  
			System.out.println("REQISIÇÃO");

			var subject = tokenService.getSubject(tokenJWT); 

			var usuario = repository.findByLogin(subject);   

			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); 

			SecurityContextHolder.getContext().setAuthentication(authentication);

			}*/

		  filterChain.doFilter(request, response);

	}

	/*private String recuperarToken(HttpServletRequest request) {
		
		var authorizationHeader = request.getHeader("Authorization");
			if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
			}
				return null;
	}*/

}
