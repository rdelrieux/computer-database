package com.excilys.computerDatabase.back.dataBase.exception;

public class UnableCreatePreparedStatmentException extends DAOException{

	
	private static final long serialVersionUID = 1L;

	public UnableCreatePreparedStatmentException(String message) {
		super(message);
	}
	
	public UnableCreatePreparedStatmentException() {
		super("Unable to create prepared statement check SQL or connection");
	}
	
	

}
