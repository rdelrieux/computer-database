package com.excilys.computerDatabase.back.service;

import java.util.List;

import com.excilys.computerDatabase.back.dataBase.dao.ComputerDAO;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Page;
import com.excilys.computerDatabase.enumeration.OrderBy;


public class ComputerService {

private static ComputerService instance ;	
private ComputerDAO computerDAO;
	
	private ComputerService() {
		this.computerDAO = ComputerDAO.getInstance();

	}

	public static ComputerService getInstance()  {
		if (instance == null) {
			instance = new ComputerService();
		}
		return instance;
	}
	
	
	public List<Computer> getListComputer(Page page, OrderBy orderBy) {
		return this.computerDAO.findAll(page,orderBy);
	}
	
	public int searchNombreElement(){
		return this.computerDAO.searchNombreElement();
	}
	
	
	public List<Computer> searchComputer(String search, Page page,OrderBy orderBy) {
		return this.computerDAO.search(search, page, orderBy);		
	}
	
	public int searchNombreElement(String search){
		return this.computerDAO.searchNombreElement(search);
	}
	
	public Computer getComputer(int id) {
		return this.computerDAO.find(id);	
	}
	
	public Computer getComputer(String name) {
		return this.computerDAO.find(name);	
	}


	public void addComputer(Computer computer) {
		this.computerDAO.addComputer(computer);
	}

	public void updateComputer( Computer computer ) {
		this.computerDAO.updateComputer(computer);
	}

	public void deletComputer(int id) {
		this.computerDAO.deletComputer(id);
	}

	
}
