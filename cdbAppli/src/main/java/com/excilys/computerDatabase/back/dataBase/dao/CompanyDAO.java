package com.excilys.computerDatabase.back.dataBase.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.back.dataBase.binding.mapper.CompanyRowMapper;
import com.excilys.computerDatabase.back.dataBase.exception.CompanyNotFoundException;
import com.excilys.computerDatabase.back.dataBase.exception.DAOException;
import com.excilys.computerDatabase.back.dataBase.exception.UnableExecutQueryException;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.logger.LoggerCdb;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CompanyDAO {

	

	private static final String REQUEST_AFFICHER_TOUTE_COMPANIES = "SELECT * FROM company";
	private static final String REQUEST_COUNT_TOUTE_COMPANIES = "SELECT COUNT(*) FROM company";
	private static final String REQUEST_TROUVER_COMPANY_FROM_ID = "SELECT id,name FROM company WHERE id = :id"
			;
	private static final String REQUEST_TROUVER_COMPANY_FROM_NAME = "SELECT * FROM company WHERE name = :name"
			;
	private static final String REQUEST_ADD_COMPANY = 
			"INSERT INTO company (name)\n"
			+ " VALUES (:name);";

	private static final String REQUEST_DELETE_COMPUTER_WITH_COMPANY = "DELETE FROM computer WHERE company_id=:id";

	private static final String REQUEST_DELETE_COMPANY = "DELETE  FROM company " + "WHERE company.id = :id \n";

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	private CompanyRowMapper companyRowMapper;

	public CompanyDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate,
			CompanyRowMapper companyRowMapper) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.jdbcTemplate = jdbcTemplate;
		this.companyRowMapper = companyRowMapper;

	}

	public List<Company> findAll() {
		List<Company> res = new ArrayList<>();
		try {

			res = jdbcTemplate.query(REQUEST_AFFICHER_TOUTE_COMPANIES, companyRowMapper);

		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("number of company not found");
		}
		return res;
	}

	public int countAll() {
		try {
			return jdbcTemplate.queryForObject(REQUEST_COUNT_TOUTE_COMPANIES, Integer.class);

		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("number of company not found");
		}
	}

	public Company find(int id) {
		try {

			MapSqlParameterSource request = new MapSqlParameterSource().addValue("id", id);
			return namedParameterJdbcTemplate.query(REQUEST_TROUVER_COMPANY_FROM_ID, request, companyRowMapper).get(0);

		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("company not found");
		} catch (IndexOutOfBoundsException e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new CompanyNotFoundException();
		}
	}

	public Company find(String name) {
		try {

			MapSqlParameterSource request = new MapSqlParameterSource().addValue("name", name);
			return namedParameterJdbcTemplate.query(REQUEST_TROUVER_COMPANY_FROM_NAME, request, companyRowMapper).get(0);

		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("company not found");
		} catch (IndexOutOfBoundsException e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new CompanyNotFoundException();
		}
	}
	
	public void addCompany(String name) {
		try  {
			SqlParameterSource companyparams = new MapSqlParameterSource().addValue("name", name);
			namedParameterJdbcTemplate.update(REQUEST_ADD_COMPANY, companyparams);
			
		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("company not added");
		}

	}

	@Transactional(rollbackFor = { DAOException.class })
	public void delete(int id) {
		try {			
			SqlParameterSource request = new MapSqlParameterSource().addValue("id", id);
			
			namedParameterJdbcTemplate.update(REQUEST_DELETE_COMPUTER_WITH_COMPANY, request);			
			namedParameterJdbcTemplate.update(REQUEST_DELETE_COMPANY, request);
			
		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("company not deleted");
		}
	}
	
	
	
}
