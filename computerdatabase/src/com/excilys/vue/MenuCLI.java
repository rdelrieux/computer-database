package com.excilys.vue;

import com.excilys.controleur.CompanyCtr;
import com.excilys.controleur.ComputerCtr;

public class MenuCLI extends CLI {
	
	private static final String MENU = "\n\n\t Menu \n\n"
			+ "Pour voir la liste des Companies tapez "+MENU_SHOW_LIST_COMPANY
			+ "\nPour voir l'identifiant d'une Company tapez "+MENU_SHOW_COMPANY
			+ "\nPour voir la liste des Computers tapez "+ MENU_SHOW_LIST_COMPUTER
			+ "\nPour voir les details d'un Computer tapez "+ MENU_SHOW_COMPUTER
			+ "\nPour ajouter un Computer tapez "+ MENU_ADD_COMPUTER
			+ "\nPour mettre a jour un Computer tapez "+MENU_UPDATE_COMPUTER
			+ "\nPour supprimer un Computer tapez "+MENU_DELETE_COMPUTER
			+ "\nPour partir tapez "+MENU_EXIT;
	
	private static final String NOT_IMPLEMENTED = "option en cour de developpement \n";
	private static final String ENTER_ID_MESSAGE = "Entez l'identifiant du Computer: ";
	private static final String IDENTIFIANT_NOT_VALID_MESSAGE = "identifiant non valid ";
	private static final String ADD_REUSSI_MESSAGE = "Computer ajoute a la list";
	
	private static MenuCLI instance ;
	private CompanyCtr companyCtr;
	private ComputerCtr computerCtr;
	
	
	private MenuCLI() {
		
		companyCtr = CompanyCtr.getInstance();
		computerCtr = ComputerCtr.getInstance();
	}

	public static MenuCLI getInstance()  {
		if (instance == null) {
			instance = new MenuCLI();
		}
		return instance;
	}
	

	public boolean isUsed() {
		System.out.println(MENU);
		int numberQuery = 0;
		boolean isValidImput = false;
		
		while ( ! isValidImput  ) {
			isValidImput = true;
			String imput = this.sc.typeString();
			
			
			switch (imput) {
			case CLI.MENU_SHOW_LIST_COMPANY:
				companyCtr.showListCompany();
					
				break;
			case CLI.MENU_SHOW_COMPANY:
				companyCtr.showCompany();
					
				break;
			case CLI.MENU_SHOW_LIST_COMPUTER:
				computerCtr.showListComputer();
				
				break;
			case CLI.MENU_SHOW_COMPUTER:
				computerCtr.showComputer();
				
				break;
			case CLI.MENU_ADD_COMPUTER:
				this.computerCtr.addComputer();
				
				break;
			case CLI.MENU_UPDATE_COMPUTER:
				this.computerCtr.updateComputer();
				
				break;
			case CLI.MENU_DELETE_COMPUTER:
				this.computerCtr.deletComputer();
				
				break;
			case CLI.MENU_EXIT:
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



	
	
	private int choixComputerId() {
		System.out.println(ENTER_ID_MESSAGE);
		String id = this.sc.typeString();
		while (! sc.isIntegerPositif(id)) {
			System.out.println(IDENTIFIANT_NOT_VALID_MESSAGE);
			System.out.println(ENTER_ID_MESSAGE);
			id=  this.sc.typeString();
		}
		return Integer.parseInt(id);
	}
	
	
	public void notImplemented() {
		System.out.println(NOT_IMPLEMENTED);
	}
	
}
