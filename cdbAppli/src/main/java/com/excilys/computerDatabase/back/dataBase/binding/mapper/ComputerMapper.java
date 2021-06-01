package com.excilys.computerDatabase.back.dataBase.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.excilys.computerDatabase.back.dataBase.binding.dto.CompanyDTOOutput;
import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOOutput;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.logger.LoggerCdb;





public class ComputerMapper {

	private static final String COLONNE_ID = "id";
	private static final String COLONNE_NAME = "name";
	private static final String COLONNE_DATE_INTRODUCED = "introduced";
	private static final String COLONNE_DATE_DISCONTINUED = "discontinued";
	private static final String COLONNE_COMPANY_ID = "company_id";
	private static final String COLONNE_COMPANY_NAME = "company.name";

	private static ComputerMapper instance;
	
	
	private ComputerMapper() {
		
		}

	public static ComputerMapper getInstance() {
		if (instance == null) {
			instance = new ComputerMapper();
		}
		return instance;
	}

	public Optional <ComputerDTOOutput> mapToComputerDTOOutput(ResultSet result) {
		
		try {
			
				return Optional.of( new ComputerDTOOutput.ComputerDTOOutputBuilder(result.getString(COLONNE_ID) ,result.getString(COLONNE_NAME))
						.withIntroduced(result.getDate(COLONNE_DATE_INTRODUCED)== null ?  "" : ""+result.getDate(COLONNE_DATE_INTRODUCED))
						.withDiscontinued(result.getDate(COLONNE_DATE_DISCONTINUED)== null ?  "" : ""+result.getDate(COLONNE_DATE_DISCONTINUED))
						.withCompany(  
								result.getString(COLONNE_COMPANY_ID)== null?  
								new CompanyDTOOutput( "","") : 
								new CompanyDTOOutput(result.getString(COLONNE_COMPANY_ID) ,result.getString(COLONNE_COMPANY_NAME))
								)
						.build());
			
		} catch (SQLException e) {
			e.printStackTrace();
			LoggerCdb.logError(ComputerMapper.class.getName(), e);
			
		}
		return Optional.empty();
	}
	
	
	public List<ComputerDTOOutput> mapToListComputerDTOOutput(ResultSet result) {
		ArrayList<ComputerDTOOutput> res = new ArrayList<>();
		
		try {
			// stream ?
			while (result.next()) {
				if (this.mapToComputerDTOOutput(result).isPresent()) {
					res.add(this.mapToComputerDTOOutput(result).get());
				}
				
				}
			
		} catch (SQLException e) {
			
			LoggerCdb.logError(ComputerMapper.class.getName(), e);
			
			
		}
		return res;
	}
	
	
	public Computer mapToComputer( ComputerDTOOutput computerDTOOutput ) {
		return new Computer.ComputerBuilder(Integer.valueOf(computerDTOOutput.getId()) ,computerDTOOutput.getName())
				
				.withIntroduced(computerDTOOutput.getIntroduced() == "" ? null : LocalDate.parse(computerDTOOutput.getIntroduced()))
				
				.withDiscontinued(computerDTOOutput.getDiscontinued() == "" ? null :LocalDate.parse(computerDTOOutput.getDiscontinued()))
				
				.withCompany(computerDTOOutput.getCompanyDTOOutput().getId() == "" ? null : new Company(
						Integer.valueOf( computerDTOOutput.getCompanyDTOOutput().getId()) , computerDTOOutput.getCompanyDTOOutput().getName())
						)
				
				.build();
	}
	
	public List<Computer> maptoListComputer( List<ComputerDTOOutput> listComputerDTOOutput) {
		
		return listComputerDTOOutput.stream()
						.map(cOut -> this.mapToComputer(cOut) )
						.collect(Collectors.toList());
	}
	

	public ComputerDTOInput  mapToComputerDTOInput( Computer computer ) {
		
		return new ComputerDTOInput.ComputerDTOInputBuilder(computer.getName())
				.withIntroduced( computer.getIntroduced() == null ? "" : ""+computer.getIntroduced() )
				.withDiscontinued(computer.getDiscontinued() == null ? "" : ""+computer.getDiscontinued())
				.withCompanyId( computer.getCompany() == null ? "" : ""+computer.getCompany().getId() )
				.build();
		
	}

	
	
	
	

	
	

}
