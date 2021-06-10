package com.excilys.computerDatabase.back.dataBase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.back.dataBase.binding.dto.CompanyDTOOutput;
import com.excilys.computerDatabase.back.dataBase.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.back.dataBase.exception.ConnectionException;
import com.excilys.computerDatabase.back.dataBase.exception.UnableCreatePreparedStatmentException;
import com.excilys.computerDatabase.back.dataBase.exception.UnableExecutQueryException;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.logger.LoggerCdb;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class CompanyDAO {

	private static final String REQUET_AFFICHER_TOUTE_COMPANIES = "SELECT * FROM company";
	private static final String REQUET_TROUVER_COMPANY_FROM_ID = "SELECT * FROM company WHERE id = ?";
	private static final String REQUET_TROUVER_COMPANY_FROM_NAME = "SELECT * FROM company WHERE name = ?";

	private static final String REQUET_DELET_COMPUTER_WITH_COMPANY = 
			"DELET  FROM computer "
			+ "LEFT JOIN company ON company.id = computer.company_id \n" 
			+ "WHERE company.id = ? \n";

	private static final String REQUET_DELET_COMPANY = 
			"DELET  FROM company " 
			+ "WHERE company.id = ? \n";
	
	
	private HikariDataSource dataSource;
	
	@Qualifier("companyMapperDAO")
	private CompanyMapper companyMapper;
	
	

	public CompanyDAO(HikariDataSource dataSource, CompanyMapper companyMapper) {
		super();
		this.dataSource = dataSource;
		this.companyMapper = companyMapper;
	}

	private PreparedStatement creatStatementFind(Connection connection, int id) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_ID);
			preparedStatement.setInt(1, id);
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableCreatePreparedStatmentException();

		}

	}

	public Company find(int id) {
		try (Connection connection = dataSource.getConnection();) {

			PreparedStatement preparedStatement = this.creatStatementFind(connection, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			CompanyDTOOutput cout = this.companyMapper.mapToCompanyDTOOutput(result);
			return this.companyMapper.mapToCompany(cout);

		} catch (SQLException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException();
		}
	}

	private PreparedStatement creatStatementFind(Connection connection, String name) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_NAME);
			preparedStatement.setString(1, name);
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableCreatePreparedStatmentException();
		}

	}

	public Company find(String name) {
		try (Connection connection = dataSource.getConnection();) {
			PreparedStatement preparedStatement = this.creatStatementFind(connection, name);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			CompanyDTOOutput cout = this.companyMapper.mapToCompanyDTOOutput(result);
			return this.companyMapper.mapToCompany(cout);
		} catch (SQLException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException();
		}
	}

	private PreparedStatement creatStatementFindAll(Connection connection) {
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(REQUET_AFFICHER_TOUTE_COMPANIES);
			return preparedStatement;

		} catch (SQLException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableCreatePreparedStatmentException();
		}

	}

	public List<Company> findAll() {
		List<Company> res = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();) {
			PreparedStatement preparedStatement = this.creatStatementFindAll(connection);
			ResultSet result = preparedStatement.executeQuery();

			if (result.isBeforeFirst()) {
				res = this.companyMapper.maptoListComputer(this.companyMapper.mapToListCompanyDTOOutput(result));
			}

		} catch (SQLException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableCreatePreparedStatmentException();
		}
		return res;
	}

	public void delet(int id) {
		try (Connection connection = dataSource.getConnection();) {

			try (PreparedStatement preparedStatement1 = connection.prepareStatement(REQUET_DELET_COMPUTER_WITH_COMPANY);
					PreparedStatement preparedStatement2 = connection.prepareStatement(REQUET_DELET_COMPANY);) {
				connection.setAutoCommit(false);
				preparedStatement1.setInt(1, id);
				preparedStatement1.execute();

				preparedStatement2.setInt(1, id);
				preparedStatement2.execute();

				connection.commit();

			} catch (SQLException e) {
				if (connection != null) {
					try {
						LoggerCdb.logError(CompanyDAO.class.getName(), e);
						connection.rollback();
					} catch (SQLException exp) {
						LoggerCdb.logError(CompanyDAO.class.getName(), exp);
					}
				}
				throw new UnableCreatePreparedStatmentException();
			} finally {
				try {
					connection.setAutoCommit(true);
				} catch (SQLException e) {
					LoggerCdb.logError(CompanyDAO.class.getName(), e);
				}
			}

		} catch (SQLException e1) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e1);
			throw new ConnectionException();
		}
	}

}
