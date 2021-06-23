package com.excilys.computerDatabase.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.computerDatabase.core.model.Company;
import com.excilys.computerDatabase.persistence.dao.CompanyDAO;


@Service
public class CompanyService {

	
	private CompanyDAO companyDao;
	
	
	public CompanyService(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}
	
	public long countAll() {
		return this.companyDao.countAll();
	}

	public List<Company> findAll() {
		return this.companyDao.findAll();
	}

	public Company find(String name) {
		return this.companyDao.find(name);
	}

	public Company find(int id) {
		return this.companyDao.find(id);
	}

	public void create(String name) {
		this.companyDao.create(name);
		
	}
	
	public void delete(int id) {
		this.companyDao.delete(id);
		
	}

	



	

	
	
}
