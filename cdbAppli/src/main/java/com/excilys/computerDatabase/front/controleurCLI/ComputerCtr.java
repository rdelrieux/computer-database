package com.excilys.computerDatabase.front.controleurCLI;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.excilys.computerDatabase.back.dataBase.dao.ComputerDAO;
import com.excilys.computerDatabase.back.dataBase.exception.ComputerNotFoundException;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.service.ComputerService;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.front.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.front.cli.CLI;
import com.excilys.computerDatabase.front.cli.ChoixUtilisateur;
import com.excilys.computerDatabase.front.cli.ComputerCLI;
import com.excilys.computerDatabase.front.session.OrderBy;
import com.excilys.computerDatabase.front.session.Page;
import com.excilys.computerDatabase.front.session.Session;
import com.excilys.computerDatabase.logger.LoggerCdb;

@Controller
public class ComputerCtr {

	private ComputerService computerService;
	private ComputerCLI computerCLI;
	private ChoixUtilisateur choixutilisateur;
	@Qualifier("computerMapperCtr")
	private ComputerMapper computerMapper;


	
	
	public ComputerCtr(ComputerService computerService, ComputerCLI computerCLI, ChoixUtilisateur choixutilisateur,
			ComputerMapper computerMapper) {
		this.computerService = computerService;
		this.computerCLI = computerCLI;
		this.choixutilisateur = choixutilisateur;
		this.computerMapper = computerMapper;
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
		try {
			Computer computer = this.computerService.getComputer(this.choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE));
			
			
			if (computer == null) {
				System.out.println("logg ComputerCtr : " + CLI.COMPUTER_NOT_FOUND_MESSAGE);
			} else {

				this.computerCLI.showComputer(computerMapper.mapToComputerDTOOutput(computer));
				ComputerDTOUpdate computerDTOUpdate = this.choixutilisateur.choixParametreUpdateComputer(computer);
					this.computerService.updateComputer(computerMapper.mapToComputer(computerDTOUpdate));
			}
		}catch (ComputerNotFoundException e) {
			LoggerCdb.logInfo(ComputerCtr.class.getName(), e);
		}
		

	}

	public void deletComputer() {
		String idS = choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE);
		try {
			int id = Integer.valueOf(idS);
			
			Computer computer = computerService.getComputer(id);
			computerCLI.showComputer(computerMapper.mapToComputerDTOOutput(computerService.getComputer(id)));
			
			if (computer != null && choixutilisateur.messageVerificationAction()) {
				this.computerService.deletComputer(id);
				System.out.println("logg ComputerCtr : " + CLI.DELET_REUSSI_MESSAGE);
			} else {
				System.out.println("logg ComputerCtr : " + CLI.DELET_CANCELED_MESSAGE);
			}
		}catch (RuntimeException e) {
			
		}
		
		
		
	}

	public void searchComputer() {
		String search = this.choixutilisateur.choixNameNotEmpty(CLI.ENTER_SEARCH_MESSAGE);
		Page page = new Page();
		page.setNombreElementRequet(this.computerService.searchNombreElementRequet(search));
		String choix = "1";
		while (!choix.equals("exit")) {
			page = choixPage( choix,  page);
			System.out.println(page);
			this.computerCLI.showListComputer(
					this.computerService.searchComputer(new Session()).stream()
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
