package com.excilys.computerDatabase.front.cli;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.front.controleurCLI.CompanyCtr;
import com.excilys.computerDatabase.front.controleurCLI.ComputerCtr;

@Component
public class MenuCLI extends CLI {
	
	private static final String MENU = "\n\n\t Menu \n\n"
			+ "Pour voir la liste des Companies tapez "+Menu.MENU_SHOW_LIST_COMPANY.getValeur()
			+ "\nPour voir l'identifiant d'une Company tapez "+Menu.MENU_SHOW_COMPANY.getValeur()
			+ "\nPour voir la liste des Computers tapez "+ Menu.MENU_SHOW_LIST_COMPUTER.getValeur()
			+ "\nPour voir les details d'un Computer tapez "+ Menu.MENU_SHOW_COMPUTER.getValeur()
			+ "\nPour ajouter un Computer tapez "+ Menu.MENU_ADD_COMPUTER.getValeur()
			+ "\nPour mettre a jour un Computer tapez "+Menu.MENU_UPDATE_COMPUTER.getValeur()
			+ "\nPour supprimer un Computer tapez "+Menu.MENU_DELETE_COMPUTER.getValeur()
			+ "\nPour faire un recherche de Computer tapez "+Menu.MENU_SEARCH_COMPUTERS.getValeur()
			+ "\nPour partir tapez "+Menu.MENU_EXIT.getValeur();
	
	@Autowired
	private CompanyCtr companyCtr;
	@Autowired
	private ComputerCtr computerCtr;
	
	private Scanner sc = new Scanner(System.in);
	
	

	public boolean isUsed() {
		System.out.println(MENU);
		int numberQuery = 0;
		boolean isValidImput = false;
		
		while ( ! isValidImput  ) {
			isValidImput = true;
			String imput = this.sc.nextLine();
		
			switch (Menu.getMenu(imput)) {
			case MENU_SHOW_LIST_COMPANY:
				this.companyCtr.showListCompany();
					
				break;
				
			case MENU_SHOW_COMPANY:
				this.companyCtr.showCompany();
					
				break;
			case MENU_SHOW_LIST_COMPUTER:
				this.computerCtr.searchComputer();
				
				break;
			case MENU_SHOW_COMPUTER:
				this.computerCtr.showComputer();
				
				break;
			case MENU_ADD_COMPUTER:
				this.computerCtr.addComputer();
				
				break;
			case MENU_UPDATE_COMPUTER:
				this.computerCtr.updateComputer();
				
				break;
			case MENU_DELETE_COMPUTER:
				this.computerCtr.deletComputer();
				
				break;
			case MENU_SEARCH_COMPUTERS:
				this.computerCtr.searchComputer();
					
				break;
			case MENU_DELETE_COMPANY:
				this.companyCtr.deletCompany();
					
				break;	
				
			case MENU_EXIT:
				return false;

			
			default: isValidImput = false;
				if (numberQuery == 2) {
					numberQuery = 0;
					System.out.println(TO_MUCH_UNVALID_TRY);
					System.out.println(MENU);
				}else {
					
					System.out.println(CLI.INPUT_ERREUR);
					numberQuery++;
				}
				
				break;

			}
			
		}
		
		return true;
	}
	
}
