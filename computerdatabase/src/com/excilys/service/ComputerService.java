package com.excilys.service;

import java.util.List;
import com.excilys.binding.dto.ComputerDTOInput;
import com.excilys.binding.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.persistence.dao.DAOFactory;

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
	
	
	public List<Computer> getListComputer() {
		return this.computerMapper.toListComputer(
				DAOFactory.getComputerDAO().findAll()
				);
	}

	public Computer getComputer(int id) {
		return this.computerMapper.toComputer(
				DAOFactory.getComputerDAO().find(id)
				);	
	}

	public void addComputer(ComputerDTOInput computerDTOInput, String companyId) {
		Computer computer = this.computerMapper.toComputer(computerDTOInput,"0" , companyId );
		if (computer != null) {
			DAOFactory.getComputerDAO().addComputer(computer);
		}else {
			System.out.println("Logg ComputerService add : Model Computer not valid");
		}
	}

	public void updateComputer( ComputerDTOInput computerDTOInput, String id , String companyId) {
		Computer computer = this.computerMapper.toComputer( computerDTOInput, id ,  companyId);
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

	public List<Computer>  searchComputer(String search, Page page) {
		
		return this.computerMapper.toListComputer(
				DAOFactory.getComputerDAO().search(search, page)
				);		
	}
	
	public int searchNombreElement(String search){
		return DAOFactory.getComputerDAO().searchNombreElement(search);
	}
	
	
}
