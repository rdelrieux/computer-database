package com.excilys.computerDatabase.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.computerDatabase.core.model.Computer;
import com.excilys.computerDatabase.core.page.Page;
import com.excilys.computerDatabase.persistence.dao.core.ComputerDAO;




@Service
public class ComputerService {

	private ComputerDAO computerDAO;
	
	public ComputerService(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}

	public List<Computer> find(Page page) {
		return this.computerDAO.find(page);
	}

	public long count(String search) {
		return this.computerDAO.count(search);
	}

	public Computer find(int id) {
		return this.computerDAO.find(id);
	}
	public void create(Computer computer) {
		this.computerDAO.create(computer);
	}

	public void update(Computer computer) {
		this.computerDAO.update(computer);
	}

	public void delete(int id) {
		this.computerDAO.delete(id);
	}

	public void delete(String listid) {
		this.computerDAO.delete(listid);		
	}

}
