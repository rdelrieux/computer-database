package com.excilys.computerDatabase.back.dataBase.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.back.dataBase.binding.mapper.ComputerRowMapper;
import com.excilys.computerDatabase.back.dataBase.exception.DAOException;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.front.session.Session;
import com.excilys.computerDatabase.logger.LoggerCdb;

@Repository
public class ComputerDAO {

	private static final String REQUET_NOMBRE_ELEMENT_SEARCH = 
			"SELECT COUNT(computer.id) AS count\n"
			+ "FROM computer \n" + "LEFT JOIN company ON company.id = computer.company_id\n"
			+ "WHERE computer.name LIKE :name " 
			+ "OR company.name LIKE :companyName " 
			+ "OR introduced LIKE :introduced "
			+ "OR discontinued LIKE :discontinued "
			;

	private static final String REQUET_AFFICHER_COMPUTERS_SEARCH = 
			"SELECT *\n" 
			+ "FROM computer \n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n" 
			+ "WHERE computer.name LIKE :name \n"
			+ "OR company.name LIKE :companyName " 
			+ "OR introduced LIKE :introduced " 
			+ "OR discontinued LIKE :discontinued " 
			+ "ORDER BY :column  :order \n"
			+ "LIMIT :offset , :nombreElement "
			;

	private static final String REQUET_TROUVER_COMPUTER_FROM_ID = 
			"SELECT *\n"
			+ "FROM computer\n"
			+ "LEFT JOIN company ON company.id = computer.company_id\n"
			+ "WHERE computer.id = :id ";


	private static final String REQUET_ADD_COMPUTER = 
			"INSERT INTO computer (name,introduced,discontinued,company_id)\n"
			+ " VALUES (:name,:introduced,:discontinued,:companyId);";

	private static final String REQUET_UPDATE_COMPUTER = 
			" UPDATE computer \n"
			+ "SET name = :name , \n"
			+ "introduced = :introduced , \n"
			+ "discontinued = :discontinued , \n" 
			+ "company_id = :companyId  \n" 
			+ "WHERE computer.id = :id ";

	private static final String REQUET_DELET_COMPUTER = 
			"DELETE FROM computer \n"
			+ "WHERE computer.id = :id ";

	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private ComputerRowMapper computerRowMapper;

	public ComputerDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
			ComputerRowMapper computerRowMapper) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.computerRowMapper = computerRowMapper;

	}
	
	public List<Computer> search(Session session) {
		List<Computer> res = new ArrayList<>();
		try {
			MapSqlParameterSource requet = new MapSqlParameterSource();
			requet.addValue("name", "%"+session.getSearch()+"%");
			requet.addValue("companyName", "%"+session.getSearch()+"%");
			requet.addValue("introduced", "%"+session.getSearch()+"%");
			requet.addValue("discontinued", "%"+session.getSearch()+"%");
			requet.addValue("column", session.getOrderBy().getColumn());
			requet.addValue("order", session.getOrder().getParamOrder());
			requet.addValue("offset", session.getPage().getOffset());
			requet.addValue("nombreElement", session.getPage().getNombreElementPage());
			
			
			res = namedParameterJdbcTemplate.query(REQUET_AFFICHER_COMPUTERS_SEARCH,requet, computerRowMapper);
			
		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
		}
		return res;
	}

	public int searchNombreElementRequet(String search) {
		try {
			
			MapSqlParameterSource requet = new MapSqlParameterSource();
			requet.addValue("name", "%"+search+"%");
			requet.addValue("companyName", "%"+search+"%");
			requet.addValue("introduced","%"+search+"%");
			requet.addValue("discontinued", "%"+search+"%");
			
			
			return namedParameterJdbcTemplate.queryForObject(REQUET_NOMBRE_ELEMENT_SEARCH,requet, Integer.class);

		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new DAOException("number of computer not found");
		}
	}

	public Computer find(int id) {
		try {

			SqlParameterSource requet = new MapSqlParameterSource().addValue("id", id);
			return namedParameterJdbcTemplate.query(REQUET_TROUVER_COMPUTER_FROM_ID, requet, computerRowMapper).get(0);

		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new DAOException("computer not found");
		} catch (IndexOutOfBoundsException e) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
			throw new DAOException("computer not found");
		}
	}
	
	
	public void addComputer(Computer computer) {
		try  {
			ComputerDTOAdd computerAdd = this.computerRowMapper.mapToComputerDTOAdd(computer);
			SqlParameterSource computerparams = new BeanPropertySqlParameterSource(computerAdd);
			namedParameterJdbcTemplate.update(REQUET_ADD_COMPUTER, computerparams);
			
		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new DAOException("computer not added");
		}

	}
	
	public void updateComputer(Computer computer) {
		try  {
			ComputerDTOUpdate computerUpdate = this.computerRowMapper.mapToComputerDTOUpdate(computer);
			SqlParameterSource computerparams = new BeanPropertySqlParameterSource(computerUpdate);
			namedParameterJdbcTemplate.update(REQUET_UPDATE_COMPUTER, computerparams);
			
		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new DAOException("computer not updated");
		}

	}
	
	public void delet(int id) {
		try {
			SqlParameterSource requet = new MapSqlParameterSource().addValue("id", id);
			namedParameterJdbcTemplate.update(REQUET_DELET_COMPUTER, requet);
			
		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new DAOException("computer not deleted");
		} 
	}

	
}
