package com.excilys.computerDatabase.core.exception.dao;

public class DAOException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	public DAOException (String message) {
		super(message);
	}
	
	public DAOException () {
		super();
	}

}
