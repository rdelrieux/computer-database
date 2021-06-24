package com.excilys.computerDatabase.core.exception.dao;

public class ConnectionException extends DAOException{
	

	private static final long serialVersionUID = 1L;

	public ConnectionException() {
		super("can't connection to database");
	}

}
