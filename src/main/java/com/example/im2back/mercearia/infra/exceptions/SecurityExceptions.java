package com.example.im2back.mercearia.infra.exceptions;

public class SecurityExceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SecurityExceptions(String msg) {
		 super(msg);
	 }
}
