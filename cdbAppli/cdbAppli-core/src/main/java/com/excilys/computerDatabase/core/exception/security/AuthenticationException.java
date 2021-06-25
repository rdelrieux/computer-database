package com.excilys.computerDatabase.core.exception.security;

public class AuthenticationException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;

	public AuthenticationException() {
		super("Incorrect username or password");
	}

}
