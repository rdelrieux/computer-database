package com.excilys.computerDatabase.back.dataBase.exception;

public class ComputerNotFoundException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public ComputerNotFoundException(String message) {
		super(message);
	}
	
	public ComputerNotFoundException() {
		super("Couldn't find in dataBase ");
	}

}
