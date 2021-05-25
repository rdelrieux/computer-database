package com.excilys.computerDatabase.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.binding.dto.CompanyDTOSQL;
import com.excilys.computerDatabase.logger.LoggerCdb;
import com.excilys.computerDatabase.model.Company;

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

	public Optional<CompanyDTOSQL> toCompanyDTOSQL(ResultSet result) {
		try {
			if (result.next()) {
				return Optional.of(new CompanyDTOSQL(
						""+result.getInt(COLONNE_ID) , result.getString(COLONNE_NAME)
						));
			}
		} catch (SQLException e) {
			LoggerCdb.logError(CompanyMapper.class.getName(), e);
			
		}
		return Optional.empty();
	}
	
	
	public Company toCompany(Optional <CompanyDTOSQL> companyDTOSQL ) {
		
		if ( companyDTOSQL.isEmpty() ){
			return null;
		}
		return new Company(
				Integer.parseInt(companyDTOSQL.get().getId()) ,companyDTOSQL.get().getName()
				);
	}
	
	
	
	public List<CompanyDTOSQL> toListCompanyDTOSQL(ResultSet result) {
		ArrayList<CompanyDTOSQL> res = new ArrayList<CompanyDTOSQL>();

		try {
			while (result.next()) {
				res.add(this.toCompanyDTOSQL(result).get());
				}
			return res;
		} catch (SQLException e) {
			
			LoggerCdb.logError(CompanyMapper.class.getName(), e);
			return new ArrayList<CompanyDTOSQL>();
		}
	}
	
	
	public List<Company> toListCompany( List<CompanyDTOSQL> listCompanyDTOSQL) {
		ArrayList<Company> res = new ArrayList<Company>();
		for (CompanyDTOSQL companyDTOSQL : listCompanyDTOSQL) {
			res.add(this.toCompany(Optional.of(companyDTOSQL)));
		}
		return res;
	}

}
