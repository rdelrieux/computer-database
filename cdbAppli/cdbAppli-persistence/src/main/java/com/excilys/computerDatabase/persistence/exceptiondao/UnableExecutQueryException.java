package com.excilys.computerDatabase.persistence.exceptiondao;

public class UnableExecutQueryException  extends DAOException{


	private static final long serialVersionUID = 1L;

	public UnableExecutQueryException(String message) {
		super(message);
	}
	
	public UnableExecutQueryException() {
		super("Query was canceled");
	}

}
