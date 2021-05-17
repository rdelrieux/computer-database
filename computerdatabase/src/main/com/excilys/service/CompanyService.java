package com.excilys.service;

import java.util.Collections;
import java.util.List;

import com.excilys.binding.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.persistence.dao.DAOFactory;



public class CompanyService {

	private static CompanyService instance ;	
	private CompanyMapper companyMapper ;
	
	private CompanyService() {
		this.companyMapper = CompanyMapper.getInstance();
	}

	public static CompanyService getInstance()  {
		if (instance == null) {
			instance = new CompanyService();
		}
		return instance;
	}
	

	public List<Company> getListCompany() {
		List<Company> listCompany = this.companyMapper.toListCompany(
				DAOFactory.getCompanyDAO().findAll()
				);
		Collections.sort(listCompany);
		
		return listCompany;
	}

	public Company getCompany(String name) {
		return this.companyMapper.toCompany(
				DAOFactory.getCompanyDAO().find(name)
				);	
	}



	

	
	
}