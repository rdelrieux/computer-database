package com.excilys.computerDatabase.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.binding.builder.CompanyBuilder;
import com.excilys.computerDatabase.binding.dto.CompanyDTOSQL;
import com.excilys.computerDatabase.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.binding.dto.ComputerDTOSQL;
import com.excilys.computerDatabase.binding.validater.ComputerValidater;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;



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
			// TODO Auto-generated catch block
			e.printStackTrace();
			
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
			
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	
	public List<Computer> toListComputer( List<ComputerDTOSQL> listComputerDTOSQL) {
		ArrayList<Computer> res = new ArrayList<Computer>();
		for (ComputerDTOSQL computerDTOSQL : listComputerDTOSQL) {
		
			res.add(this.toComputer(Optional.of(computerDTOSQL)));
			
		}
		return res;
	}

	public Computer toComputer(ComputerDTOInput computerDTOInput) {
		if ( ! computerValidater.validate(computerDTOInput)) {
			return null;
		}
		
		return new Computer.ComputerBuilder(0,computerDTOInput.getName())
				
				.withIntroduced(computerDTOInput.getIntroduced() == "" ? null : LocalDate.parse(computerDTOInput.getIntroduced()))
				
				.withDiscontinued(computerDTOInput.getDiscontinued() == "" ? null :LocalDate.parse(computerDTOInput.getDiscontinued()))
				
				.withCompany(computerDTOInput.getCompanyId() == "" ? null : new Company (
						Integer.parseInt(computerDTOInput.getCompanyId()),""
						))
				.build();
	}



	
	
	
	/*
	
	public ComputerDTOUser toComputerDTO(Computer computer) {
		return new ComputerDTOSQLBuilder()
		
		.setId(String.valueOf(computer.getId()))
		
		.setName(computer.getName())
		
		.setIntroduced(computer.getIntroduced() == null ? "" : computer.getIntroduced().toString())
		
		.setDiscontinued(computer.getDiscontinued() == null ? "" : computer.getDiscontinued().toString())
	
		.setCompanyId(computer.getCompany() == null ? "" : String.valueOf(computer.getCompany().getId()))
		
		.setCompanyName(computer.getCompany() == null ? "" : computer.getCompany().getName())
		
		.build();
	
	}
	
	public Computer toComputer(ComputerDTOUser computerDTOUser) {
		if ( ! computerValidater.validate(computerDTOUser)) {
			return null;
		}
		
		return new ComputerBuilder()
		
		.setId(Integer.parseInt(computerDTOUser.getId()))
		
		.setName(computerDTOUser.getName())
		
		.setIntroduced(computerDTOUser.getIntroduced() == "" ? null : LocalDate.parse(computerDTOUser.getIntroduced()))
		
		.setDiscontinued(computerDTOUser.getDiscontinued() == "" ? null :LocalDate.parse(computerDTOUser.getDiscontinued()))
		
	
		.setCompany(computerDTOUser.getCompanyName() == "" ? new Company() : new Company(Integer.parseInt(computerDTOUser.getCompanyId()) , computerDTOUser.getCompanyName()))
		
		.build();
		
	
	}
	
*/
	
	
	
	
	
	
	
	

	

}
