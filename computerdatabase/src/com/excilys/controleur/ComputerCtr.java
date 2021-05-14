package com.excilys.controleur;

import com.excilys.binding.dto.ComputerDTOInput;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.vue.CLI;
import com.excilys.vue.ChoixUtilisateur;
import com.excilys.vue.CompanyCLI;
import com.excilys.vue.ComputerCLI;

public class ComputerCtr {

	private static ComputerCtr instance ;
	private ComputerService computerService;
	private ComputerCLI computerCLI;
	private CompanyService companyService;
	private ChoixUtilisateur choixutilisateur;
	
	
	private ComputerCtr() {
		this.computerService = ComputerService.getInstance();
		this.computerCLI = ComputerCLI.getInstance();
		this.companyService = CompanyService.getInstance();
		this.choixutilisateur = ChoixUtilisateur.getInstance();
		
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

	public void showComputer() {
		Computer computer = computerService.getComputer(choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE));
		computerCLI.showComputer(computer);
	}
	
	

	public void addComputer() {
		ComputerDTOInput computerDTOInput = choixutilisateur.choixParametreAddComputer();
		if (computerDTOInput.getCompanyName().isEmpty()) {
			this.computerService.addComputer(computerDTOInput , null );
		}else {
			Company company = this.companyService.getCompany(computerDTOInput.getCompanyName());
			if (company != null) {
				this.computerService.addComputer(computerDTOInput , company );
			}else {
				System.out.println("logg ComputerCtr : "+CLI.COMPANY_NOT_FOUND_MESSAGE);
			}
			
		}
		}

	public void updateComputer() {
		Computer computer = computerService.getComputer(choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE));
		if (computer == null) {
			System.out.println("logg ComputerCtr : "+CLI.COMPUTER_NOT_FOUND_MESSAGE);
		}else {
			computerCLI.showComputer(computer);
			ComputerDTOInput computerDTOInput = choixutilisateur.choixParametreUpdateComputer(computer);	
			System.out.println("logg ComputerCtr update : "+computerDTOInput);

		if (computerDTOInput.getCompanyName().isEmpty()) {
			this.computerService.updateComputer(computerDTOInput,null );
			System.out.println("logg ComputerCtr : UPDATE successfull");

		}else {
			Company company = this.companyService.getCompany(computerDTOInput.getCompanyName());
			if (company != null) {
				this.computerService.updateComputer( computerDTOInput , company );
				System.out.println("logg ComputerCtr : UPDATE successfull");

			}else {
				System.out.println("logg ComputerCtr : "+CLI.COMPANY_NOT_FOUND_MESSAGE);
			}
			
		}
		}
		
	}

	public void deletComputer() {
		int id = choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE);
		computerCLI.showComputer(computerService.getComputer(id));
		if (choixutilisateur.messageVerificationAction()) {
			this.computerService.deletComputer(id);
			System.out.println("logg ComputerCtr : DELET successfull");

		}else {
			System.out.println("logg ComputerCtr : DELET cancel");
		}
		
		
	}
	
	
	

	

	
	
	
	

	
	
}
