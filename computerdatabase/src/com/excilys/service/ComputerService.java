package com.excilys.service;

import java.util.List;

import com.excilys.mapping.ComputerMapping;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.DAO;
import com.excilys.persistence.dao.DAOFactory;
import com.excilys.persistence.dao.implement.ComputerDAO;

public class ComputerService {

private static ComputerService instance ;	
private ComputerMapping computerMapping;
	
	private ComputerService() {
		computerMapping = ComputerMapping.getInstance();

	}

	public static ComputerService getInstance()  {
		if (instance == null) {
			instance = new ComputerService();
		}
		return instance;
	}
	
	
	public List<Computer> getListComputer() {
		DAO<Computer> computerDao = DAOFactory.getComputerDAO();
		List<Computer> listCompany = computerDao.findAll();		
		return listCompany;
	}

	public Computer getComputerDetail(int id) {
		DAO<Computer> computerDao = DAOFactory.getComputerDAO();
		Computer computer = computerDao.find(id);
		return computer;
	}

	public void addComputer(String[] computerInformation, Company company) {
		DAO<Computer> computerDao = DAOFactory.getComputerDAO();
		((ComputerDAO) computerDao).addComputer(computerMapping.toComputer(computerInformation, company));
	}

	public void updateComputer(String[] computerInformation, Company company, int id) {
		DAO<Computer> computerDao = DAOFactory.getComputerDAO();
		Computer computer =  getComputerDetail(id) ;
		((ComputerDAO) computerDao).updateComputer(computerMapping.toComputer(computer ,computerInformation, company));
	}
	
	
	
}
