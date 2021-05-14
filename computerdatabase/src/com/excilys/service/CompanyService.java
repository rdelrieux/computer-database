package com.excilys.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapping.CompanyMapping;
import com.excilys.model.Company;
import com.excilys.persistence.dao.DAO;
import com.excilys.persistence.dao.DAOFactory;
import com.excilys.persistence.dao.implement.CompanyDAO;
import com.excilys.vue.CLI;



public class CompanyService {

	private static CompanyService instance ;	
	
	private CompanyService() {
		
	}

	public static CompanyService getInstance()  {
		if (instance == null) {
			instance = new CompanyService();
		}
		return instance;
	}
	
	
	
	public List<Company> getListCompany() {
		DAO<Company> companyDao = DAOFactory.getCompanyDAO();
		List<Company> listCompany = companyDao.findAll();		
		return listCompany;
	}

	public Company getCompany(String name) {
		DAO<Company> companyDao = DAOFactory.getCompanyDAO();
		Company company = null;
		if ( ! name.equals(CLI.UNCHANGED) ) {
			company =((CompanyDAO)companyDao).find(name);
		}
		 
		return company;
		
	}

	
	
}
