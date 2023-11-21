package com.example.im2back.mercearia.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

public class RecursoCriadoEvento extends ApplicationEvent{
	private static final long serialVersionUID = 1L;

	@Getter	@Setter
	private HttpServletRequest request;
	@Getter @Setter
	private Model model;
	
	public RecursoCriadoEvento(Object source, Model model, HttpServletRequest request) {
		super(source);
		this.request = request;
		this.model = model;
	
	}

}