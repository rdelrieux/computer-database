package com.excilys.computerDatabase.persistence.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.binding.dto.CompanyDTOSQL;
import com.excilys.computerDatabase.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.connection.CdbConnection;

public class CompanyDAO {

	private static final String REQUET_AFFICHER_TOUTE_COMPANIES = "SELECT * FROM company";
	private static final String REQUET_TROUVER_COMPANY_FROM_ID = "SELECT * FROM company WHERE id = ?";
	private static final String REQUET_TROUVER_COMPANY_FROM_NAME = "SELECT * FROM company WHERE name = ?";

	private static CompanyDAO instance;
	private CompanyMapper companyMapper;
	private CdbConnection cdbConnection;

	private CompanyDAO() {
		this.cdbConnection = CdbConnection.getInstance();
		this.companyMapper = CompanyMapper.getInstance();
	}

	public static CompanyDAO getInstance() {
		if (instance == null) {
			instance = new CompanyDAO();
		}
		return instance;
	}

	public PreparedStatement creatStatementFind(Connection connection, int id) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_ID);
			preparedStatement.setInt(1, id);
			return preparedStatement;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}

	public CompanyDTOSQL find(int id) {
		CompanyDTOSQL res = new CompanyDTOSQL();
		try (Connection connection = cdbConnection.getConnection();
				PreparedStatement preparedStatement = this.creatStatementFind(connection, id);
				ResultSet result = preparedStatement.executeQuery();) {
			
			if (result.isBeforeFirst() ) { 
				res = this.companyMapper.toCompanyDTOSQL(result);
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public PreparedStatement creatStatementFind(Connection connection, String name) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_NAME);
			preparedStatement.setString(1, name);
			return preparedStatement;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public CompanyDTOSQL find(String name) {
		CompanyDTOSQL res = new CompanyDTOSQL();
		try (Connection connection = cdbConnection.getConnection();
				PreparedStatement preparedStatement = this.creatStatementFind(connection, name);
				ResultSet result = preparedStatement.executeQuery();) {

			if (result.isBeforeFirst() ) { 
			res = this.companyMapper.toCompanyDTOSQL(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public PreparedStatement creatStatementFindAll(Connection connection) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_AFFICHER_TOUTE_COMPANIES);
			return preparedStatement;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<CompanyDTOSQL> findAll() {
		List<CompanyDTOSQL> res = new ArrayList<CompanyDTOSQL>();
		try (Connection connection = cdbConnection.getConnection();
				PreparedStatement preparedStatement = this.creatStatementFindAll(connection);
				ResultSet result = preparedStatement.executeQuery();) {
			
			if (result.isBeforeFirst() ) { 
				res = this.companyMapper.toListCompanyDTOSQL(result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
