package com.excilys.computerDatabase.back.dataBase.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.excilys.computerDatabase.back.dataBase.binding.dto.CompanyDTOOutput;
import com.excilys.computerDatabase.back.dataBase.exception.CompanyNotFoundException;
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

	public CompanyDTOOutput mapToCompanyDTOOutput(ResultSet result) {
	
		try {
			return new CompanyDTOOutput(result.getString(COLONNE_ID) ,result.getString(COLONNE_NAME));	
		} catch (SQLException e) {
			LoggerCdb.logError(ComputerMapper.class.getName(), e);
			throw new CompanyNotFoundException();
		}
	}
	
	
	public List<CompanyDTOOutput> mapToListCompanyDTOOutput(ResultSet result) {
		List<CompanyDTOOutput> res = new ArrayList<>();
		
			try {
				while (result.next()) {
					res.add(this.mapToCompanyDTOOutput(result));
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
