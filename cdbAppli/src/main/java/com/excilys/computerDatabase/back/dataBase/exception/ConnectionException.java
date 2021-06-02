package com.excilys.computerDatabase.back.dataBase.exception;

public class ConnectionException extends DAOException{
	

	private static final long serialVersionUID = 1L;

	public ConnectionException() {
		super("can't connection to database");
	}

}
