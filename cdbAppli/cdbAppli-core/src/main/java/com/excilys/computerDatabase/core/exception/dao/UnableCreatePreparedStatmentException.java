package com.excilys.computerDatabase.core.exception.dao;

public class UnableCreatePreparedStatmentException extends DAOException{

	
	private static final long serialVersionUID = 1L;

	public UnableCreatePreparedStatmentException(String message) {
		super(message);
	}
	
	public UnableCreatePreparedStatmentException() {
		super("Unable to create prepared statement check SQL or connection");
	}
	
	

}
