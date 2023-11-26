package com.example.im2back.mercearia.infra.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

	@Autowired
	SecurityFilter securityFilter;

	
	@Bean
	@SuppressWarnings("removal")
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception,RuntimeException {

		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(req -> {
					req.requestMatchers(HttpMethod.POST, "/login").permitAll();
					req.requestMatchers(HttpMethod.GET, "/login").permitAll();
					req.requestMatchers("/css/**").permitAll();
					req.requestMatchers("/images/**").permitAll();
					req.requestMatchers(HttpMethod.POST, "/cliente/delete").hasRole("ADMIN");
					req.requestMatchers(HttpMethod.POST, "/produtos/delete").hasRole("ADMIN");	
					try {
						req.anyRequest().authenticated().and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
						 .exceptionHandling(exceptions -> exceptions
						            .accessDeniedHandler((request, response, accessDeniedException) -> {
						                handleAccessDeniedException( accessDeniedException, request, response);
						            })
						    );
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}).build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	private void handleAccessDeniedException(AccessDeniedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
       var token = request.getParameter("token");
       String mensagem = "Você não tem autorização para acessar esse recurso !";
       response.sendRedirect("/cliente/home?token=" + token + "&permissao=" + URLEncoder.encode(mensagem, "UTF-8"));
    }

}
