package com.example.im2back.mercearia.infra.exceptions;

public class ServiceExceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceExceptions(String msg) {
		 super(msg);
	 }
}
