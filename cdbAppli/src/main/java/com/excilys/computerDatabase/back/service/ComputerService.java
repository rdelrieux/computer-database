package com.excilys.computerDatabase.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerDatabase.back.dataBase.dao.ComputerDAO;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Page;
import com.excilys.computerDatabase.enumeration.OrderBy;
import com.excilys.computerDatabase.front.session.Session;

@Service
public class ComputerService {

	private ComputerDAO computerDAO;
	
	public ComputerService(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}

	public List<Computer> searchComputer(Session session) {
		return this.computerDAO.search(session);
	}

	public int searchNombreElementRequet(String search) {
		return this.computerDAO.searchNombreElementRequet(search);
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
