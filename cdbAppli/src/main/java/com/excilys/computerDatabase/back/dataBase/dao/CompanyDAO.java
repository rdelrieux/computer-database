package com.excilys.computerDatabase.back.dataBase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.sql.DataSource;

import com.excilys.computerDatabase.back.dataBase.binding.dto.CompanyDTOOutput;
import com.excilys.computerDatabase.back.dataBase.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.back.dataBase.connection.CdbConnection;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.logger.LoggerCdb;

public class CompanyDAO {

	private static final String REQUET_AFFICHER_TOUTE_COMPANIES = "SELECT * FROM company";
	private static final String REQUET_TROUVER_COMPANY_FROM_ID = "SELECT * FROM company WHERE id = ?";
	private static final String REQUET_TROUVER_COMPANY_FROM_NAME = "SELECT * FROM company WHERE name = ?";
	
	private static final String REQUET_DELET_COMPUTER_WITH_COMPANY = "DELET  FROM computer "
			+ "LEFT JOIN company ON company.id = computer.company_id \n"
			+ "WHERE company.id = ? \n"
			;
	
	private static final String REQUET_DELET_COMPANY = "DELET  FROM company "
			+ "WHERE company.id = ? \n"
			;

	
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

	public PreparedStatement creatStatementFind(int id) {
		try (Connection connection = cdbConnection.getConnection();){

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_ID);
			preparedStatement.setInt(1, id);
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			return null;
		}

	}

	public Company  find(int id) {
		try  {
			PreparedStatement preparedStatement = this.creatStatementFind( id);
			ResultSet result = preparedStatement.executeQuery();
			if (result.isBeforeFirst() ) { 
				Optional <CompanyDTOOutput> cout = this.companyMapper.mapToCompanyDTOOutput(result) ;
				if (cout.isPresent()) {
					return this.companyMapper.mapToCompany (cout.get());
				}else {
					//exception 
				}
			}
		} catch (SQLException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
		}
		return null;
	}

	public PreparedStatement creatStatementFind(String name) {
		try (Connection connection = cdbConnection.getConnection();){

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_NAME);
			preparedStatement.setString(1, name);
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
		}
		return null;
	}

	public Company find(String name) {
		try  {
			PreparedStatement preparedStatement = this.creatStatementFind( name);
			ResultSet result = preparedStatement.executeQuery();

			if (result.isBeforeFirst() ) {
				result.next();
				Optional <CompanyDTOOutput> cout = this.companyMapper.mapToCompanyDTOOutput(result) ;
				if (cout.isPresent()) {
					return this.companyMapper.mapToCompany (cout.get());
				}else {
					//exception 
				}
			}
			
		} catch (SQLException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
		}
		return null;
	}

	public PreparedStatement creatStatementFindAll() {
		try (Connection connection = cdbConnection.getConnection();){

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_AFFICHER_TOUTE_COMPANIES);
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
		}
		return null;
	}

	public List<Company> findAll() {
		List<Company> res = new ArrayList<>();
		try  {
			PreparedStatement preparedStatement = this.creatStatementFindAll();
			ResultSet result = preparedStatement.executeQuery();
			
			if (result.isBeforeFirst() ) { 
				res = this.companyMapper.maptoListComputer( this.companyMapper.mapToListCompanyDTOOutput(result)) ;
			}

		} catch (SQLException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
		}
		return res;
	}

	
	
	
	public void delet(int id) {
		try (Connection connection = CdbConnection.getInstance().getConnection();){
				
		
		try  (PreparedStatement preparedStatement1 = connection.prepareStatement(REQUET_DELET_COMPUTER_WITH_COMPANY);
			PreparedStatement preparedStatement2 = connection.prepareStatement(REQUET_DELET_COMPANY);) {
			connection.setAutoCommit(false);
			preparedStatement1.setInt(1, id);
			preparedStatement1.execute();
			
			preparedStatement2.setInt(1, id);
			preparedStatement2.execute();

			connection.commit();

		}  catch (SQLException e) {
			if (connection != null) {
				try {
					LoggerCdb.logError(CompanyDAO.class.getName(), e);
					connection.rollback();
				} catch (SQLException exp) {
					LoggerCdb.logError(CompanyDAO.class.getName(), exp);
				}
			}
		}
		 finally {
			 try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				LoggerCdb.logError(CompanyDAO.class.getName(), e);
			}
	    }
		
		} catch (SQLException e1) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e1);
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
