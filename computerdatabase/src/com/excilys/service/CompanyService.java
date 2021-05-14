package com.excilys.service;

import java.util.Collections;
import java.util.List;

import com.excilys.binding.mapping.CompanyMapping;
import com.excilys.model.Company;
import com.excilys.persistence.dao.DAOFactory;



public class CompanyService {

	private static CompanyService instance ;	
	private CompanyMapping companyMapping ;
	
	private CompanyService() {
		companyMapping = CompanyMapping.getInstance();
	}

	public static CompanyService getInstance()  {
		if (instance == null) {
			instance = new CompanyService();
		}
		return instance;
	}
	

	public List<Company> getListCompany() {
		List<Company> listCompany = companyMapping.toListCompany(
				DAOFactory.getCompanyDAO().findAll()
				);
		Collections.sort(listCompany);
		
		return listCompany;
	}

	public Company getCompany(String name) {
		return companyMapping.toCompany(
				DAOFactory.getCompanyDAO().find(name)
				);	
	}



	

	
	
}
