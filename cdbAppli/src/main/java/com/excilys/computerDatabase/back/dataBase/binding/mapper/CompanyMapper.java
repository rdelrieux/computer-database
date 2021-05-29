package com.excilys.computerDatabase.back.dataBase.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.excilys.computerDatabase.back.dataBase.binding.dto.CompanyDTOOutput;
import com.excilys.computerDatabase.back.model.Company;


import com.excilys.computerDatabase.logger.LoggerCdb;

public class CompanyMapper {
	
	private static final String COLONNE_ID = "id";
	private static final String COLONNE_NAME = "name";

	private static CompanyMapper instance;
	
	
	private CompanyMapper() {
		
		}

	public static CompanyMapper getInstance() {
		if (instance == null) {
			instance = new CompanyMapper();
		}
		return instance;
	}

	public Optional <CompanyDTOOutput> mapToCompanyDTOOutput(ResultSet result) {
	
		try {
				return Optional.of( new CompanyDTOOutput(result.getString(COLONNE_ID) ,result.getString(COLONNE_NAME)));	
		} catch (SQLException e) {
			LoggerCdb.logError(ComputerMapper.class.getName(), e);
			
		}
		return Optional.empty();
	}
	
	
	public List<CompanyDTOOutput> mapToListCompanyDTOOutput(ResultSet result) {
		ArrayList<CompanyDTOOutput> res = new ArrayList<>();
		
		try {
			// stream ?
			while (result.next()) {
				if (this.mapToCompanyDTOOutput(result).isPresent()) {
					res.add(this.mapToCompanyDTOOutput(result).get());
				}
				
				}
			
		} catch (SQLException e) {
			
			LoggerCdb.logError(ComputerMapper.class.getName(), e);
			
			
		}
		return res;
	}
	
	
	public Company mapToCompany( CompanyDTOOutput companyDTOOutput ) {
		return new Company(Integer.valueOf(companyDTOOutput.getId()) ,companyDTOOutput.getName());
		
	}
	
	public List<Company> maptoListComputer( List<CompanyDTOOutput> listCompanyDTOOutput) {
		
		return listCompanyDTOOutput.stream()
						.map(cOut -> this.mapToCompany(cOut) )
						.collect(Collectors.toList());
	}
	
	
	

}
