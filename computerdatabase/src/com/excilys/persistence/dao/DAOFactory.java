package com.excilys.persistence.dao;

import java.sql.Connection;

import com.excilys.connection.CdbConnection;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.implement.CompanyDAO;
import com.excilys.persistence.dao.implement.ComputerDAO;

public class DAOFactory {

	protected static final Connection conn = CdbConnection.getInstance();

	/**
	 * Retourne un objet Company interagissant avec la BDD
	 * @return DAO
	 */
	public static DAO getCompanyDAO() {
		return new CompanyDAO(conn);
	}

	public static DAO getComputer() {
		return new ComputerDAO(conn);
	}

} 
