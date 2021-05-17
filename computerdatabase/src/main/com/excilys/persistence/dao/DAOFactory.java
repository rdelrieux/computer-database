package com.excilys.persistence.dao;

import com.excilys.connection.CdbConnection;
import com.excilys.persistence.dao.implement.CompanyDAO;
import com.excilys.persistence.dao.implement.ComputerDAO;

public class DAOFactory {

	protected static final CdbConnection cdbconnection = CdbConnection.getInstance();

	/**
	 * Retourne un objet Company interagissant avec la BDD
	 * @return DAO
	 */
	public static CompanyDAO getCompanyDAO() {
		return CompanyDAO.getInstance(cdbconnection.getConnection());
	}

	public static ComputerDAO getComputerDAO() {
		return ComputerDAO.getInstance(cdbconnection.getConnection());
	}

} 