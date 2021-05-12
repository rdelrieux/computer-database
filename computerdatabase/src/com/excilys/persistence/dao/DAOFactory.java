package com.excilys.persistence.dao;

import java.sql.Connection;

import com.excilys.connection.CdbConnection;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.implement.CompanyDAO;
import com.excilys.persistence.dao.implement.ComputerDAO;

public class DAOFactory {

	protected static final CdbConnection cdbconnection = CdbConnection.getInstance();

	/**
	 * Retourne un objet Company interagissant avec la BDD
	 * @return DAO
	 */
	public static DAO<Company> getCompanyDAO() {
		return CompanyDAO.getInstance(cdbconnection.getConnection());
	}

	public static DAO<Computer> getComputerDAO() {
		return ComputerDAO.getInstance(cdbconnection.getConnection());
	}

} 
