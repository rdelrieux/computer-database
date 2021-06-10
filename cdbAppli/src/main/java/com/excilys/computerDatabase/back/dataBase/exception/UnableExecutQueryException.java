package com.excilys.computerDatabase.back.dataBase.exception;

public class UnableExecutQueryException  extends DAOException{


	private static final long serialVersionUID = 1L;

	public UnableExecutQueryException(String message) {
		super(message);
	}
	
	public UnableExecutQueryException() {
		super("Query was canceled");
	}

}
