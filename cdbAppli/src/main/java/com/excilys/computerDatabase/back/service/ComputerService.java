package com.excilys.computerDatabase.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerDatabase.back.dataBase.dao.ComputerDAO;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Page;
import com.excilys.computerDatabase.enumeration.OrderBy;

@Service
public class ComputerService {

	@Autowired
	private ComputerDAO computerDAO;

	public List<Computer> searchComputer(String search, Page page, OrderBy orderBy) {
		return this.computerDAO.search(search, page, orderBy);
	}

	public int searchNombreElement(String search) {
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

	public void updateComputer(Computer computer) {
		this.computerDAO.updateComputer(computer);
	}

	public void deletComputer(int id) {
		this.computerDAO.deletComputer(id);
	}

}
