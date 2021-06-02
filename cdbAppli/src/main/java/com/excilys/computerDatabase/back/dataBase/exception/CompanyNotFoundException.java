package com.excilys.computerDatabase.back.dataBase.exception;

public class CompanyNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException() {
		super("Couldn't find in dataBase ");
	}

}
