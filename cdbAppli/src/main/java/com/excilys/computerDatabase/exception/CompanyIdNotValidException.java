package com.excilys.computerDatabase.exception;

public class CompanyIdNotValidException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public  CompanyIdNotValidException(String message) {
        super(message);
    }

}
