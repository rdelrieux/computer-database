package com.excilys.computerDatabase.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.binding.builder.CompanyBuilder;
import com.excilys.computerDatabase.binding.builder.CompanyDTOSQLBuilder;
import com.excilys.computerDatabase.binding.dto.CompanyDTOSQL;
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
				return Optional.of(new CompanyDTOSQLBuilder()
						.setId(""+result.getInt(COLONNE_ID))
						.setName(result.getString(COLONNE_NAME))
						.build());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return Optional.empty();
	}
	
	public Company toCompany(CompanyDTOSQL companyDTOSQL) {
		
		return new CompanyBuilder()
		.setId(Integer.parseInt(companyDTOSQL.getId()))
		.setName(companyDTOSQL.getName())
		.build();
	}
	
	
	public Company toCompany(Optional <CompanyDTOSQL> companyDTOSQL ) {
		
		if ( companyDTOSQL.isEmpty() ){
			return null;
		}
		return new CompanyBuilder()
				.setId(Integer.parseInt(companyDTOSQL.get().getId()))
				.setName(companyDTOSQL.get().getName())
				.build();
		
	}
	
	
	
	public List<CompanyDTOSQL> toListCompanyDTOSQL(ResultSet result) {
		ArrayList<CompanyDTOSQL> res = new ArrayList<CompanyDTOSQL>();

		try {
			while (result.next()) {
				res.add(new CompanyDTOSQLBuilder()
						.setId(""+result.getInt(COLONNE_ID))
						.setName(result.getString(COLONNE_NAME))
						.build() );
				}
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Company> toListCompany( List<CompanyDTOSQL> listCompanyDTOSQL) {
		ArrayList<Company> res = new ArrayList<Company>();
		for (CompanyDTOSQL companyDTOSQL : listCompanyDTOSQL) {
			res.add(this.toCompany(companyDTOSQL));
		}
		return res;
	}

}
