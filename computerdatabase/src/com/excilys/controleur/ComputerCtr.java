package com.excilys.controleur;

import com.excilys.model.Company;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.vue.CLI;
import com.excilys.vue.CompanyCLI;
import com.excilys.vue.ComputerCLI;

public class ComputerCtr {

	private static ComputerCtr instance ;
	private ComputerService computerService;
	private ComputerCLI computerCLI;
	private CompanyService companyService;
	
	
	
	private ComputerCtr() {
		this.computerService = ComputerService.getInstance();
		this.computerCLI = ComputerCLI.getInstance();
		this.companyService = CompanyService.getInstance();
		
	}

	public static ComputerCtr getInstance()  {
		if (instance == null) {
			instance = new ComputerCtr();
		}
		return instance;
	}
	

	public void showListComputer() {
		computerCLI.showListComputer(computerService.getListComputer());
	}
	
	public boolean showComputerDetail(int id) {
		return computerCLI.showComputerDetail(computerService.getComputerDetail(id), id);
		
	}

	public boolean addComputer() {
		String[] computerInformation = computerCLI.choixParametreAddComputer();
		if (computerCLI.messageVerificationAction(computerInformation, "ADD: ")) {
			this.computerService.addComputer(computerInformation, this.companyService.getCompany(computerInformation[3]));
			return true;
		}
		return false;
	}

	public boolean updateComputer(int choixComputerId) {
		String[] computerInformation = computerCLI.choixParametreUpdateComputer();
		Company company = null;
		if (computerCLI.messageVerificationAction(computerInformation, "UPDATE: ")) {
			if (! computerInformation[3].equals(CLI.UNCHANGED)) {
				company =  this.companyService.getCompany(computerInformation[3]);
			}
			this.computerService.updateComputer(computerInformation, company, choixComputerId);
			return true;
		}
		return false;
		
	}
	
	
	

	
	
}
