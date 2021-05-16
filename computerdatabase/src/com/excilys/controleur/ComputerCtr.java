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

	private static ComputerCtr instance;
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

	public static ComputerCtr getInstance() {
		if (instance == null) {
			instance = new ComputerCtr();
		}
		return instance;
	}

	public void showListComputer() {
		this.computerCLI.showListComputer(
				this.computerService.getListComputer()
				);
	}

	public void showComputer() {
		Computer computer = this.computerService.getComputer(
				this.choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE)
				);
		this.computerCLI.showComputer(computer);
	}

	public void addComputer() {
		ComputerDTOInput computerDTOInput = this.choixutilisateur.choixParametreAddComputer();
		if (computerDTOInput.getCompanyName().isEmpty()) {
			this.computerService.addComputer(computerDTOInput,"" );
		} else {
			Company company = this.companyService.getCompany(computerDTOInput.getCompanyName());
			if (company != null) {
				this.computerService.addComputer(computerDTOInput, ""+company.getId());
			} else {
				System.out.println("logg ComputerCtr : " + CLI.COMPANY_NOT_FOUND_MESSAGE);
			}

		}
	}

	public void updateComputer() {
		Computer computer = this.computerService.getComputer(
				this.choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE)
				);
		if (computer == null) {
			System.out.println("logg ComputerCtr : " + CLI.COMPUTER_NOT_FOUND_MESSAGE);
		} else {
			
			this.computerCLI.showComputer(computer);
			ComputerDTOInput computerDTOInput = this.choixutilisateur.choixParametreUpdateComputer(computer);

			 
			if (computerDTOInput.getCompanyName().isEmpty()) {
				this.computerService.updateComputer(computerDTOInput, ""+computer.getId(), "");
				System.out.println("logg ComputerCtr : UPDATE successfull");

			} else {
				Company company = this.companyService.getCompany(computerDTOInput.getCompanyName());
				if (company != null) {
					this.computerService.updateComputer(computerDTOInput, ""+computer.getId(), ""+company.getId());
					System.out.println("logg ComputerCtr : UPDATE successfull");
				} else {
					System.out.println("logg ComputerCtr : " + CLI.COMPANY_NOT_FOUND_MESSAGE);
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

		} else {
			System.out.println("logg ComputerCtr : DELET cancel");
		}

	}

}
