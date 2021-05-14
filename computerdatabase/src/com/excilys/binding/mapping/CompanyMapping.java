package com.excilys.binding.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.binding.builder.CompanyBuilder;
import com.excilys.binding.builder.CompanyDTOSQLBuilder;
import com.excilys.binding.dto.CompanyDTOSQL;
import com.excilys.model.Company;

public class CompanyMapping {

	private static final String COLONNE_ID = "id";
	private static final String COLONNE_NAME = "name";

	private static CompanyMapping instance;

	private CompanyMapping() {

	}

	public static CompanyMapping getInstance() {
		if (instance == null) {
			instance = new CompanyMapping();
		}
		return instance;
	}

	public CompanyDTOSQL toCompanyDTOSQL(ResultSet result) {
		try {
			if (result.first()) {
				return new CompanyDTOSQLBuilder()
						.setId(""+result.getInt(COLONNE_ID))
						.setName(result.getString(COLONNE_NAME))
						.build();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
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
	
	public Company toCompany(CompanyDTOSQL companyDTOSQL) {
		if (companyDTOSQL == null){
			return null;
		}
		return new CompanyBuilder()
		.setId(Integer.parseInt(companyDTOSQL.getId()))
		.setName(companyDTOSQL.getName())
		.build();
	}
	
	public List<Company> toListCompany( List<CompanyDTOSQL> listCompanyDTOSQL) {
		ArrayList<Company> res = new ArrayList<Company>();
		for (CompanyDTOSQL companyDTOSQL : listCompanyDTOSQL) {
			res.add(this.toCompany(companyDTOSQL));
		}
		return res;
	}

}
