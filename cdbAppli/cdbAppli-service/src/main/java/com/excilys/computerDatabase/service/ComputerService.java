package com.excilys.computerDatabase.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.computerDatabase.core.model.Computer;
import com.excilys.computerDatabase.core.page.Page;
import com.excilys.computerDatabase.persistence.dao.ComputerDAO;




@Service
public class ComputerService {

	private ComputerDAO computerDAO;
	
	public ComputerService(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}

	public List<Computer> searchComputer(Page page) {
		return this.computerDAO.search(page);
	}

	public long searchNombreElementRequet(String search) {
		return this.computerDAO.searchNombreElementRequet(search);
	}

	public Computer getComputer(int id) {
		return this.computerDAO.find(id);
	}
	public void addComputer(Computer computer) {
		this.computerDAO.addComputer(computer);
	}

	public void updateComputer(Computer computer) {
		this.computerDAO.updateComputer(computer);
	}

	public void deleteComputer(int id) {
		this.computerDAO.delete(id);
	}

	public void delete(String listid) {
		this.computerDAO.delete(listid);		
	}

}
