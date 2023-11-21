package com.example.im2back.mercearia.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.example.im2back.mercearia.event.RecursoCriadoEvento;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvento> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvento event) {
		HttpServletRequest request = event.getRequest();
		Model model = event.getModel();
		
		adicionarToken(request, model);	
	}

	private void adicionarToken(HttpServletRequest request, Model model) {
		var token = request.getParameter("token");
		model.addAttribute("token",token);
	}

}
