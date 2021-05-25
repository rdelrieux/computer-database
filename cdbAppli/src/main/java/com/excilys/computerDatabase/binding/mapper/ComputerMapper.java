package com.excilys.computerDatabase.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.binding.dto.ComputerDTOSQL;
import com.excilys.computerDatabase.binding.validater.ComputerValidater;
import com.excilys.computerDatabase.logger.LoggerCdb;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.persistence.dao.implement.ComputerDAO;



public class ComputerMapper {

	private static final String COLONNE_ID = "id";
	private static final String COLONNE_NAME = "name";
	private static final String COLONNE_DATE_INTRODUCED = "introduced";
	private static final String COLONNE_DATE_DISCONTINUED = "discontinued";
	private static final String COLONNE_COMPANY_ID = "company_id";
	private static final String COLONNE_COMPANY_NAME = "company.name";

	private static ComputerMapper instance;
	private ComputerValidater computerValidater;
	
	private ComputerMapper() {
		this.computerValidater = ComputerValidater.getInstance();
		}

	public static ComputerMapper getInstance() {
		if (instance == null) {
			instance = new ComputerMapper();
		}
		return instance;
	}

	public Optional <ComputerDTOSQL> toComputerDTOSQL(ResultSet result) {
	
		try {
				return Optional.of( new ComputerDTOSQL.ComputerDTOSQLBuilder(
						result.getString(COLONNE_ID),result.getString(COLONNE_NAME)
						)
						.withIntroduced(result.getDate(COLONNE_DATE_INTRODUCED)== null ?  "" : result.getDate(COLONNE_DATE_INTRODUCED).toString()   )
						.withDiscontinued(result.getDate(COLONNE_DATE_DISCONTINUED)== null ?  "" : result.getDate(COLONNE_DATE_DISCONTINUED).toString()   )
						.withCompanyId(result.getString(COLONNE_COMPANY_ID)== null ? "" : ""+result.getInt(COLONNE_COMPANY_ID) )
						.withCompanyName(result.getString(COLONNE_COMPANY_NAME)== null ? "" : ""+result.getString(COLONNE_COMPANY_NAME) )
						.build() );
			
		} catch (SQLException e) {
			LoggerCdb.logError(ComputerMapper.class.getName(), e);
			
		}
		return Optional.empty();
	}
	

	public Computer toComputer(Optional <ComputerDTOSQL> computerDTOSQL ) {
		
	
		if ( computerDTOSQL.isEmpty() ){
			return null;
		}
		
		return new Computer.ComputerBuilder(
				Integer.parseInt(computerDTOSQL.get().getId()),computerDTOSQL.get().getName()
				)
				
				.withIntroduced(computerDTOSQL.get().getIntroduced() == "" ? null : LocalDate.parse(computerDTOSQL.get().getIntroduced()))
				
				.withDiscontinued(computerDTOSQL.get().getDiscontinued() == "" ? null :LocalDate.parse(computerDTOSQL.get().getDiscontinued()))
				
				
				.withCompany(computerDTOSQL.get().getCompanyId() == "" ? null : new Company (
						Integer.valueOf(computerDTOSQL.get().getCompanyId()),
						computerDTOSQL.get().getCompanyName()))
				
				.build();
		
	}

	public List<ComputerDTOSQL> toListComputerDTOSQL(ResultSet result) {
		ArrayList<ComputerDTOSQL> res = new ArrayList<ComputerDTOSQL>();
		
		try {
			while (result.next()) {
				if (this.toComputerDTOSQL(result).isPresent()) {
					res.add(this.toComputerDTOSQL(result).get());
				}
				
				}
			
			return res;
		} catch (SQLException e) {
			
			LoggerCdb.logError(ComputerMapper.class.getName(), e);
			
			
		}
		return new ArrayList<ComputerDTOSQL>();
	}
	
	
	
	public List<Computer> toListComputer( List<ComputerDTOSQL> listComputerDTOSQL) {
		ArrayList<Computer> res = new ArrayList<Computer>();
		for (ComputerDTOSQL computerDTOSQL : listComputerDTOSQL) {
		
			res.add(this.toComputer(Optional.of(computerDTOSQL)));
			
		}
		return res;
	}

	public Computer toComputer(ComputerDTOInput computerDTOInput) {
	
			computerValidater.validate(computerDTOInput);
			
			return new Computer.ComputerBuilder(0,computerDTOInput.getName())
					
					.withIntroduced(computerDTOInput.getIntroduced() == "" ? null : LocalDate.parse(computerDTOInput.getIntroduced()))
					
					.withDiscontinued(computerDTOInput.getDiscontinued() == "" ? null :LocalDate.parse(computerDTOInput.getDiscontinued()))
					
					.withCompany(computerDTOInput.getCompanyId() == "" ? null : new Company (
							Integer.parseInt(computerDTOInput.getCompanyId()),""
							))
					.build();	
		
	}
	

}
