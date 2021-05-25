package com.excilys.computerDatabase.service;

import java.util.List;

import com.excilys.computerDatabase.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Page;
import com.excilys.computerDatabase.persistence.dao.DAOFactory;

public class ComputerService {

private static ComputerService instance ;	
private ComputerMapper computerMapper;
	
	private ComputerService() {
		this.computerMapper = ComputerMapper.getInstance();

	}

	public static ComputerService getInstance()  {
		if (instance == null) {
			instance = new ComputerService();
		}
		return instance;
	}
	
	
	public List<Computer> getListComputer(Page page) {
		
		return this.computerMapper.toListComputer(
				DAOFactory.getComputerDAO().findAll(page)
				);
	}
	public int searchNombreElement(){
		return DAOFactory.getComputerDAO().searchNombreElement();
	}
	
	
	public List<Computer>  searchComputer(String search, Page page) {
		
		return this.computerMapper.toListComputer(
				DAOFactory.getComputerDAO().search(search, page)
				);		
	}
	public int searchNombreElement(String search){
		return DAOFactory.getComputerDAO().searchNombreElement(search);
	}
	
	
	public Computer getComputer(int id) {
		return this.computerMapper.toComputer(
				DAOFactory.getComputerDAO().find(id)
				);	
	}

	public void addComputer(ComputerDTOInput computerDTOInput) {
		Computer computer = this.computerMapper.toComputer(computerDTOInput);
		DAOFactory.getComputerDAO().addComputer(computer);
	}

	public void updateComputer( ComputerDTOInput computerDTOInput, String id , String companyId) {
		Computer computer = this.computerMapper.toComputer( computerDTOInput);
		System.out.println("Logg ComputerService update :"+ computer);

		if (computer != null) {
			DAOFactory.getComputerDAO().updateComputer(computer);
		}else {
			System.out.println("Logg ComputerService update : Model Computer not valid");
		}
		
	}

	public void deletComputer(int id) {
		DAOFactory.getComputerDAO().deletComputer(id);
	}


	
}
