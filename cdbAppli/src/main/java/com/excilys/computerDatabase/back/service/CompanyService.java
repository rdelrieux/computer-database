package com.excilys.computerDatabase.back.service;

import java.util.List;

import com.excilys.computerDatabase.back.dataBase.dao.CompanyDAO;
import com.excilys.computerDatabase.back.model.Company;


public class CompanyService {

	private static CompanyService instance ;
	private CompanyDAO companyDao;
	
	private CompanyService() {
		this.companyDao = CompanyDAO.getInstance();
	}

	public static CompanyService getInstance()  {
		if (instance == null) {
			instance = new CompanyService();
		}
		return instance;
	}
	

	public List<Company> getListCompany() {
		return this.companyDao.findAll();
	}

	public Company getCompany(String name) {
		return this.companyDao.find(name);
	}




	

	
	
}
