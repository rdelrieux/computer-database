package com.excilys.computerDatabase.exception;

public class NameNotValidException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public  NameNotValidException(String message) {
        super(message);
    }

}
