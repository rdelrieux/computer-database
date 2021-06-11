package com.excilys.computerDatabase.back.dataBase.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.back.model.Company;

@Component()
public class CompanyRowMapper  implements RowMapper<Company>  {
	
	
	private static final String COLONNE_ID = "id";
	
	private static final String COLONNE_NAME = "name";
	
	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Company(rs.getInt(COLONNE_ID) ,rs.getString(COLONNE_NAME));
	}
	

	

}
