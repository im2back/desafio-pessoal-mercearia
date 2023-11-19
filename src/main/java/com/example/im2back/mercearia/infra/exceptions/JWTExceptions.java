package com.example.im2back.mercearia.infra.exceptions;

public class JWTExceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JWTExceptions(String msg) {
		 super(msg);
	 }
}
