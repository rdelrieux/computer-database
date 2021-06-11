package com.excilys.computerDatabase.back.dataBase.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOAdd.ComputerDTOAddBuilder;
import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOUpdate.ComputerDTOUpdateBuilder;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Computer.ComputerBuilder;

@Component("computerMapperDAO")
public class ComputerRowMapper implements RowMapper<Computer>{

	private static final String COLONNE_ID = "id";
	private static final String COLONNE_NAME = "name";
	private static final String COLONNE_DATE_INTRODUCED = "introduced";
	private static final String COLONNE_DATE_DISCONTINUED = "discontinued";
	private static final String COLONNE_COMPANY_ID = "company_id";
	private static final String COLONNE_COMPANY_NAME = "company.name";
	

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		ComputerBuilder computer = new ComputerBuilder(rs.getInt(COLONNE_ID) ,rs.getString(COLONNE_NAME));	
				
				if ( rs.getDate(COLONNE_DATE_INTRODUCED) != null ) {
					computer.withIntroduced(LocalDate.parse(""+rs.getDate(COLONNE_DATE_INTRODUCED)));
				}
				if ( rs.getDate(COLONNE_DATE_DISCONTINUED) != null ) {
					computer.withDiscontinued(LocalDate.parse(""+rs.getDate(COLONNE_DATE_DISCONTINUED)));
				}
				if ( rs.getString(COLONNE_COMPANY_ID) != null ) {
					computer.withCompany(new Company(Integer.valueOf(""+rs.getString(COLONNE_COMPANY_ID)),
							rs.getString(COLONNE_COMPANY_NAME)));
				}
		
		return computer.build();
		}

	
	public ComputerDTOAdd mapToComputerDTOAdd (Computer computer) {
		ComputerDTOAddBuilder builder = new ComputerDTOAdd.ComputerDTOAddBuilder(computer.getName());
		
		if ( computer.getIntroduced() != null ) {
			builder.withIntroduced(""+ computer.getIntroduced() );
		}
		if ( computer.getDiscontinued() != null ) {
			builder.withDiscontinued(""+computer.getDiscontinued());
		}
		if ( computer.getCompany() != null ) {
			builder.withCompanyId(""+computer.getCompany().getId());
		}
		return builder.build();
		
		
	}
	
	public ComputerDTOUpdate mapToComputerDTOUpdate (Computer computer) {
		ComputerDTOUpdateBuilder builder = new ComputerDTOUpdate.ComputerDTOUpdateBuilder(""+computer.getId(), computer.getName());
		
		if ( computer.getIntroduced() != null ) {
			builder.withIntroduced(""+ computer.getIntroduced() );
		}
		if ( computer.getDiscontinued() != null ) {
			builder.withDiscontinued(""+computer.getDiscontinued());
		}
		if ( computer.getCompany() != null ) {
			builder.withCompanyId(""+computer.getCompany().getId());
		}
		return builder.build();
		
	}
	
	
	
}
