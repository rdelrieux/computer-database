package com.excilys.computerDatabase.persistence.dao;

import com.excilys.computerDatabase.connection.CdbConnection;
import com.excilys.computerDatabase.persistence.dao.implement.CompanyDAO;
import com.excilys.computerDatabase.persistence.dao.implement.ComputerDAO;

public class DAOFactory {


	/**
	 * Retourne un objet Company interagissant avec la BDD
	 * @return DAO
	 */
	public static CompanyDAO getCompanyDAO() {
		return CompanyDAO.getInstance();
	}

	public static ComputerDAO getComputerDAO() {
		return ComputerDAO.getInstance();
	}

} 
