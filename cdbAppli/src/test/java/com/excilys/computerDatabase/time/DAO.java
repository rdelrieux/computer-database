package com.excilys.computerDatabase.time;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.back.dataBase.binding.mapper.ComputerRowMapper;
import com.excilys.computerDatabase.back.dataBase.dao.CompanyDAO;
import com.excilys.computerDatabase.back.dataBase.exception.UnableExecutQueryException;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.front.session.Session;
import com.excilys.computerDatabase.logger.LoggerCdb;

@Component
public class DAO {

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
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private ComputerRowMapper computerRowMapper;

	public DAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
			ComputerRowMapper computerRowMapper) {
		super();
		
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.computerRowMapper = computerRowMapper;


	}
	
	@Timed
	public List<Computer> search(Session session) {
		List<Computer> res = new ArrayList<>();
		try {
			LoggerCdb.logDebug("start search");
			
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
			 try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		} catch (DataAccessException e) {
			LoggerCdb.logError(CompanyDAO.class.getName(), e);
			throw new UnableExecutQueryException("search erreur");
		}
		LoggerCdb.logDebug("end search");
		return res;
	}
	
	
}
