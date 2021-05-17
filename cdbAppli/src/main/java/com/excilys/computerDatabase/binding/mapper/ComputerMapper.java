package com.excilys.computerDatabase.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.binding.builder.ComputerBuilder;
import com.excilys.computerDatabase.binding.builder.ComputerDTOSQLBuilder;
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

	public ComputerDTOSQL toComputerDTOSQL(ResultSet result) {
		try {
			if (result.first()) {
				return new ComputerDTOSQLBuilder()
						.setId(""+result.getInt(COLONNE_ID))
						.setName(result.getString(COLONNE_NAME))
						.setIntroduced(result.getDate(COLONNE_DATE_INTRODUCED)== null ?  "" : result.getDate(COLONNE_DATE_INTRODUCED).toString()   )
						.setDiscontinued(result.getDate(COLONNE_DATE_DISCONTINUED)== null ?  "" : result.getDate(COLONNE_DATE_DISCONTINUED).toString()   )
						.setCompanyId(result.getInt(COLONNE_COMPANY_ID)== 0 ? "" : ""+result.getInt(COLONNE_COMPANY_ID) )
						.setCompanyName(result.getString(COLONNE_COMPANY_NAME)== null ? "" : ""+result.getString(COLONNE_COMPANY_NAME) )
						.build();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}

	public List<ComputerDTOSQL> toListComputerDTOSQL(ResultSet result) {
		ArrayList<ComputerDTOSQL> res = new ArrayList<ComputerDTOSQL>();

		try {
			while (result.next()) {
				res.add(new ComputerDTOSQLBuilder()
						.setId(""+result.getInt(COLONNE_ID))
						.setName(result.getString(COLONNE_NAME))
						.setIntroduced(result.getDate(COLONNE_DATE_INTRODUCED)== null ?  "" : result.getDate(COLONNE_DATE_INTRODUCED).toString()   )
						.setDiscontinued(result.getDate(COLONNE_DATE_DISCONTINUED)== null ?  "" : result.getDate(COLONNE_DATE_DISCONTINUED).toString()   )
						.setCompanyId(result.getInt(COLONNE_COMPANY_ID)== 0 ? "" : ""+result.getInt(COLONNE_COMPANY_ID) )
						.setCompanyName(result.getString(COLONNE_COMPANY_NAME)== null ? "" : result.getString(COLONNE_COMPANY_NAME) )
						.build());
				}
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Computer toComputer(ComputerDTOSQL computerDTOSQL ) {
		if (computerDTOSQL == null){
			return null;
		}
		return new ComputerBuilder()
				.setId(Integer.parseInt(computerDTOSQL.getId()))
				
				.setName(computerDTOSQL.getName())
				
				.setIntroduced(computerDTOSQL.getIntroduced() == "" ? null : LocalDate.parse(computerDTOSQL.getIntroduced()))
				
				.setDiscontinued(computerDTOSQL.getDiscontinued() == "" ? null :LocalDate.parse(computerDTOSQL.getDiscontinued()))
				
				
				.setCompany(computerDTOSQL.getCompanyId() == "" ? null : new Company (
						Integer.valueOf(computerDTOSQL.getCompanyId()),
						computerDTOSQL.getCompanyName()))
				
				.build();
	}
	
	
	public List<Computer> toListComputer( List<ComputerDTOSQL> listComputerDTOSQL) {
		ArrayList<Computer> res = new ArrayList<Computer>();
		for (ComputerDTOSQL computerDTOSQL : listComputerDTOSQL) {
			res.add(this.toComputer(computerDTOSQL));
		}
		return res;
	}

	public Computer toComputer(ComputerDTOInput computerDTOInput, String id , String companyId) {
		if ( ! computerValidater.validate(computerDTOInput)) {
			return null;
		}
		
		return new ComputerBuilder()
				
				.setId(Integer.parseInt(id))
				
				.setName(computerDTOInput.getName())
				
				.setIntroduced(computerDTOInput.getIntroduced() == "" ? null : LocalDate.parse(computerDTOInput.getIntroduced()))
				
				.setDiscontinued(computerDTOInput.getDiscontinued() == "" ? null :LocalDate.parse(computerDTOInput.getDiscontinued()))
				
				.setCompany(companyId == "" ? null : new Company (Integer.parseInt(companyId), computerDTOInput.getCompanyName()) )
				
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