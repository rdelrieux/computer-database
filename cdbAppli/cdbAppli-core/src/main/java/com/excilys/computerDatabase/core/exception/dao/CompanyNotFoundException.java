package com.excilys.computerDatabase.core.exception.dao;

public class CompanyNotFoundException extends DAOException {
	
	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException() {
		super("Couldn't find in dataBase ");
	}

}
