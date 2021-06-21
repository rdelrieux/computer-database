package com.excilys.computerDatabase.persistence.exceptiondao;

public class DAOException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	public DAOException (String message) {
		super(message);
	}

}
