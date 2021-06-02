package com.excilys.computerDatabase.front.controleurCLI;

import java.util.stream.Collectors;

import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Page;
import com.excilys.computerDatabase.back.service.CompanyService;
import com.excilys.computerDatabase.back.service.ComputerService;
import com.excilys.computerDatabase.enumeration.OrderBy;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.front.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.front.cli.CLI;
import com.excilys.computerDatabase.front.cli.ChoixUtilisateur;
import com.excilys.computerDatabase.front.cli.ComputerCLI;

public class ComputerCtr {

	private static ComputerCtr instance;
	private ComputerService computerService;
	private ComputerCLI computerCLI;
	private CompanyService companyService;
	private ChoixUtilisateur choixutilisateur;
	private ComputerMapper computerMapper;

	private ComputerCtr() {
		this.computerService = ComputerService.getInstance();
		this.computerCLI = ComputerCLI.getInstance();
		this.companyService = CompanyService.getInstance();
		this.choixutilisateur = ChoixUtilisateur.getInstance();
		this.computerMapper = ComputerMapper.getInstance();
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
			this.computerCLI.showListComputer(
					this.computerService.getListComputer( page, OrderBy.COMPUTER_NAME).stream()
					.map(c -> computerMapper.mapToComputerDTOOutput(c))
					.collect(Collectors.toList())
					);
			choix = this.choixutilisateur.choixName(CLI.ENTER_PAGE_MESSAGE);
		}
		
	}

	public void showComputer() {
		Computer computer = this.computerService.getComputer(this.choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE));
		this.computerCLI.showComputer(computerMapper.mapToComputerDTOOutput(computer));
	}

	public void addComputer() {
		ComputerDTOAdd computerDTOAdd = this.choixutilisateur.choixParametreAddComputer();
		
		this.computerService.addComputer(this.computerMapper.mapToComputer(computerDTOAdd));
		
	}

	public void updateComputer() {
		Computer computer = this.computerService.getComputer(this.choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE));
		if (computer == null) {
			System.out.println("logg ComputerCtr : " + CLI.COMPUTER_NOT_FOUND_MESSAGE);
		} else {

			this.computerCLI.showComputer(computerMapper.mapToComputerDTOOutput(computer));
			ComputerDTOUpdate computerDTOUpdate = this.choixutilisateur.choixParametreUpdateComputer(computer);
				this.computerService.updateComputer(computerMapper.mapToComputer(computerDTOUpdate));
		}

	}

	public void deletComputer() {
		int id = choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE);
		Computer computer = computerService.getComputer(id);
		computerCLI.showComputer(computerMapper.mapToComputerDTOOutput(computerService.getComputer(id)));
		
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
			this.computerCLI.showListComputer(
					this.computerService.searchComputer(search, page, OrderBy.COMPUTER_NAME).stream()
					.map(c -> this.computerMapper.mapToComputerDTOOutput(c))
					.collect(Collectors.toList())
					);
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
