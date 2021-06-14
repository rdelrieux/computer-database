package com.excilys.computerDatabase.back.dataBase.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.back.dataBase.binding.mapper.ComputerRowMapper;
import com.excilys.computerDatabase.back.dataBase.exception.ComputerNotFoundException;
import com.excilys.computerDatabase.back.dataBase.exception.UnableExecutQueryException;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.front.session.Session;
import com.excilys.computerDatabase.logger.LoggerCdb;


import com.excilys.computerDatabase.logger.time.Timed;

@Repository
public class ComputerDAO {

	private static final String REQUEST_NOMBRE_ELEMENT_SEARCH = 
			"SELECT COUNT(computer.id) AS count\n"
			+ "FROM computer \n" + "LEFT JOIN company ON company.id = computer.company_id\n"
			+ "WHERE computer.name LIKE :name " 
			+ "OR company.name LIKE :companyName " 
			+ "OR introduced LIKE :introduced "
			+ "OR discontinued LIKE :discontinued "
			;

	private static final String REQUEST_AFFICHER_COMPUTERS_SEARCH = 
			"SELECT *\n" 
			+ "FROM computer \n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n" 
			+ "WHERE computer.name LIKE :name \n"
			+ "OR company.name LIKE :companyName " 
			+ "OR introduced LIKE :introduced " 
			+ "OR discontinued LIKE :discontinued " 
			+ "ORDER BY :column :order \n"
			+ "LIMIT :offset , :nombreElement "
			;

	private static final String REQUEST_TROUVER_COMPUTER_FROM_ID = 
			"SELECT *\n"
			+ "FROM computer\n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n"
			+ "WHERE computer.id = :id ";


	private static final String REQUEST_ADD_COMPUTER = 
			"INSERT INTO computer (name,introduced,discontinued,company_id)\n"
			+ " VALUES (:name,:introduced,:discontinued,:companyId);";

	private static final String REQUEST_UPDATE_COMPUTER = 
			" UPDATE computer \n"
			+ "SET name = :name , \n"
			+ "introduced = :introduced , \n"
			+ "discontinued = :discontinued , \n" 
			+ "company_id = :companyId  \n" 
			+ "WHERE computer.id = :id ";

	private static final String REQUEST_DELETE_COMPUTER = 
			"DELETE FROM computer \n"
			+ "WHERE computer.id = :id ";

	private static final String REQUEST_DELETE_LIST_COMPUTER = 
			"DELETE FROM computer \n"
			+ "WHERE computer.id IN (:id) ";

	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private ComputerRowMapper computerRowMapper;
	private JdbcTemplate jdbcTemplate;

	public ComputerDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
			ComputerRowMapper computerRowMapper, JdbcTemplate jdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.computerRowMapper = computerRowMapper;
		this.jdbcTemplate = jdbcTemplate;


	}
	
	@Timed
	public List<Computer> search(Session session) {
		List<Computer> res = new ArrayList<>();
		try {
			String sql = REQUEST_AFFICHER_COMPUTERS_SEARCH.replace(":column", session.getOrderBy().getColumn());
			sql = sql.replace(":order", session.getOrder().getParamOrder());
			
			MapSqlParameterSource requestParam = new MapSqlParameterSource();
			requestParam.addValue("name", "%"+session.getSearch()+"%");
			requestParam.addValue("companyName", "%"+session.getSearch()+"%");
			requestParam.addValue("introduced", "%"+session.getSearch()+"%");
			requestParam.addValue("discontinued", "%"+session.getSearch()+"%");
			requestParam.addValue("offset", session.getPage().getOffset());
			requestParam.addValue("nombreElement", session.getPage().getNombreElementPage());
			
			res = namedParameterJdbcTemplate.query(sql,requestParam, computerRowMapper);
			
		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("search erreur");
		}
		return res;
	}

	@Timed
	public int searchNombreElementRequet(String search) {
		try {
			
			MapSqlParameterSource requestParam = new MapSqlParameterSource();
			requestParam.addValue("name", "%"+search+"%");
			requestParam.addValue("companyName", "%"+search+"%");
			requestParam.addValue("introduced","%"+search+"%");
			requestParam.addValue("discontinued", "%"+search+"%");
			
			
			return namedParameterJdbcTemplate.queryForObject(REQUEST_NOMBRE_ELEMENT_SEARCH,requestParam, Integer.class);

		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("number of computer not found");
		}
	}

	@Timed
	public Computer find(int id) {
		try {

			SqlParameterSource request = new MapSqlParameterSource().addValue("id", id);
			return namedParameterJdbcTemplate.query(REQUEST_TROUVER_COMPUTER_FROM_ID, request, computerRowMapper).get(0);

		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("computer not found");
		} catch (IndexOutOfBoundsException e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new ComputerNotFoundException();
		}
	}
	
	@Timed
	public void addComputer(Computer computer) {
		try  {
			ComputerDTOAdd computerAdd = this.computerRowMapper.mapToComputerDTOAdd(computer);
			SqlParameterSource computerparams = new BeanPropertySqlParameterSource(computerAdd);
			namedParameterJdbcTemplate.update(REQUEST_ADD_COMPUTER, computerparams);
			
		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("computer not added");
		}

	}
	
	@Timed
	public void updateComputer(Computer computer) {
		try  {
			ComputerDTOUpdate computerUpdate = this.computerRowMapper.mapToComputerDTOUpdate(computer);
			SqlParameterSource computerparams = new BeanPropertySqlParameterSource(computerUpdate);
			namedParameterJdbcTemplate.update(REQUEST_UPDATE_COMPUTER, computerparams);
			
		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("computer not updated");
		}

	}
	
	@Timed
	public void delete(int id) {
		try {
			SqlParameterSource requestParam = new MapSqlParameterSource().addValue("id", id);
			namedParameterJdbcTemplate.update(REQUEST_DELETE_COMPUTER, requestParam);
			
		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("computer not deleted");
		} 
	}

	@Timed
	public void delete(String listid) {
		try {
			
			String sql = REQUEST_DELETE_LIST_COMPUTER.replace(":id", listid);
			this.jdbcTemplate.update(sql);
			
		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("computer not deleted");
		} 
		
	}

	
}
