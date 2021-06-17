package com.excilys.computerDatabase.back.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.computerDatabase.back.dataBase.dao.ComputerDAO;
import com.excilys.computerDatabase.back.model.Computer;
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
