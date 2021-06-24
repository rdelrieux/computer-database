package com.excilys.computerDatabase.core.exception.dao;

public class ComputerNotFoundException extends DAOException{


	private static final long serialVersionUID = 1L;

	public ComputerNotFoundException(String message) {
		super(message);
	}
	
	public ComputerNotFoundException() {
		super("Couldn't find in dataBase ");
	}

}
