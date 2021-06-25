package com.excilys.computerDatabase.core.security;

public class AuthenticationToken {
	
	private final String jwt;
	
	public AuthenticationToken (String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	
}
