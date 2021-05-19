package com.excilys.computerDatabase.controleur;

import com.excilys.computerDatabase.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Page;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.vue.CLI;
import com.excilys.computerDatabase.vue.ChoixUtilisateur;
import com.excilys.computerDatabase.vue.ComputerCLI;

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
		Page page = new Page();
		page.setNombreElementRequet(this.computerService.searchNombreElement());
		String choix = "1";
		while (!choix.equals("exit")) {
			page = choixPage( choix,  page);
			System.out.println(page);
			this.computerCLI.showListComputer(this.computerService.getListComputer( page));
			choix = this.choixutilisateur.choixName(CLI.ENTER_PAGE_MESSAGE);
		}
		
	}

	public void showComputer() {
		Computer computer = this.computerService.getComputer(this.choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE));
		this.computerCLI.showComputer(computer);
	}

	public void addComputer() {
		ComputerDTOInput computerDTOInput = this.choixutilisateur.choixParametreAddComputer();
		if (computerDTOInput.getCompanyName().isEmpty()) {
			this.computerService.addComputer(computerDTOInput, "");
			System.out.println("logg ComputerCtr : " + CLI.ADD_REUSSI_MESSAGE);
		} else {
			Company company = this.companyService.getCompany(computerDTOInput.getCompanyName());
			if (company != null) {
				this.computerService.addComputer(computerDTOInput, "" + company.getId());
				System.out.println("logg ComputerCtr : " + CLI.ADD_REUSSI_MESSAGE);
			} else {
				System.out.println("logg ComputerCtr : " + CLI.COMPANY_NOT_FOUND_MESSAGE);
			}

		}
	}

	public void updateComputer() {
		Computer computer = this.computerService.getComputer(this.choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE));
		if (computer == null) {
			System.out.println("logg ComputerCtr : " + CLI.COMPUTER_NOT_FOUND_MESSAGE);
		} else {

			this.computerCLI.showComputer(computer);
			ComputerDTOInput computerDTOInput = this.choixutilisateur.choixParametreUpdateComputer(computer);

			if (computerDTOInput.getCompanyName().isEmpty()) {
				this.computerService.updateComputer(computerDTOInput, "" + computer.getId(), "");
				System.out.println("logg ComputerCtr : " + CLI.UPDATE_REUSSI_MESSAGE);

			} else {
				Company company = this.companyService.getCompany(computerDTOInput.getCompanyName());
				if (company != null) {
					this.computerService.updateComputer(computerDTOInput, "" + computer.getId(), "" + company.getId());
					System.out.println("logg ComputerCtr : " + CLI.UPDATE_REUSSI_MESSAGE);
				} else {
					System.out.println("logg ComputerCtr : " + CLI.COMPANY_NOT_FOUND_MESSAGE);
				}

			}
		}

	}

	public void deletComputer() {
		int id = choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE);
		Computer computer = computerService.getComputer(id);
		computerCLI.showComputer(computerService.getComputer(id));
		if (computer != null && choixutilisateur.messageVerificationAction()) {
			this.computerService.deletComputer(id);
			System.out.println("logg ComputerCtr : " + CLI.DELET_REUSSI_MESSAGE);
		} else {
			System.out.println("logg ComputerCtr : " + CLI.DELET_CANCELED_MESSAGE);
		}
	}

	public void searchComputer() {
		String search = this.choixutilisateur.choixNameNotEmpty(CLI.ENTER_SEARCH_MESSAGE);
		Page page = new Page();
		page.setNombreElementRequet(this.computerService.searchNombreElement(search));
		String choix = "1";
		while (!choix.equals("exit")) {
			page = choixPage( choix,  page);
			System.out.println(page);
			this.computerCLI.showListComputer(this.computerService.searchComputer(search, page));
			choix = this.choixutilisateur.choixName(CLI.ENTER_PAGE_MESSAGE);
		}
		
	}

	public Page choixPage(String choix, Page page) {
		
			if (choix.equals("next")) {
				page.setPageAfter();
			} else if (choix.equals("before")) {
				page.setPageBefore();
			} else {
				try {
					int choixInt = Integer.parseInt(choix);
					if (choixInt > 0 && choixInt < (page.getNombreElementRequet() / page.getNombreElementPage() + 2)) {
						page.setNumPage(choixInt);
					}

				} catch (Exception c) {
					System.out.println(c + "logging erreur choix page Contol Computer");
				}
			}
			return page;
		}

}
