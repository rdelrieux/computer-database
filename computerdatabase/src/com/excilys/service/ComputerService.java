package com.excilys.service;

import java.util.List;
import com.excilys.binding.dto.ComputerDTOInput;
import com.excilys.binding.mapping.ComputerMapping;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.DAOFactory;

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
		List<Computer> listComputer = computerMapping.toListComputer(
				DAOFactory.getComputerDAO().findAll()
				);
		
		return listComputer;
	}

	public Computer getComputer(int id) {
		return computerMapping.toComputer(
				DAOFactory.getComputerDAO().find(id)
				);	
	}

	public void addComputer(ComputerDTOInput computerDTOInput, Company company) {
		Computer computer = computerMapping.toComputer(computerDTOInput , company);
		if (computer != null) {
			DAOFactory.getComputerDAO().addComputer(computer);
		}else {
			System.out.println("Logg ComputerService add : Model Company not valid");
		}
	}

	public void updateComputer( ComputerDTOInput computerDTOInput, Company company) {
		Computer computer = computerMapping.toComputer( computerDTOInput , company);
		System.out.println("Logg ComputerService update :"+ computer);

		if (computer != null) {
			DAOFactory.getComputerDAO().updateComputer(computer);
		}else {
			System.out.println("Logg ComputerService update : Model Company not valid");
		}
		
	}

	public void deletComputer(int id) {
		DAOFactory.getComputerDAO().deletComputer(id);
	}
	
	
}
