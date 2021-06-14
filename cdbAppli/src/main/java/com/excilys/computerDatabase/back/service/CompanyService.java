package com.excilys.computerDatabase.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerDatabase.back.dataBase.dao.CompanyDAO;
import com.excilys.computerDatabase.back.model.Company;

@Service
public class CompanyService {

	
	private CompanyDAO companyDao;
	
	
	
	public CompanyService(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}

	public List<Company> getListCompany() {
		return this.companyDao.findAll();
	}

	public Company getCompany(String name) {
		return this.companyDao.find(name);
	}

	public Company getCompany(int id) {
		return this.companyDao.find(id);
	}

	public void deletCompany(int id) {
		this.companyDao.delete(id);
		
	}



	

	
	
}
