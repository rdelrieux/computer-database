package com.excilys.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	
	public List<Company> toListCompany(ResultSet resultSet) throws SQLException {
		ArrayList<Company> res = new ArrayList<Company>();
			while(resultSet.next()) {
				Company company =  new Company(resultSet.getInt(COLONNE_ID), resultSet.getString(COLONNE_NAME) );
				res.add(company);	
			}
		return res;	
	}

	public Company toCompany(ResultSet result) throws SQLException {
		Company company = new Company();
		if(result.first()) {
				company = new Company(result.getInt(COLONNE_ID), result.getString(COLONNE_NAME) );
		}
		return company;		
	}

	
	
	
}
