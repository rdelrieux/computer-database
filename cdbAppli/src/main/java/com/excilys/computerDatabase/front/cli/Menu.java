package com.excilys.computerDatabase.front.cli;

public enum Menu {
	
	MENU_SHOW_LIST_COMPANY("1"),
	MENU_SHOW_COMPANY("2"),
	MENU_SHOW_LIST_COMPUTER("3"),
	MENU_SHOW_COMPUTER("4"),
	MENU_ADD_COMPUTER("5"),
	MENU_UPDATE_COMPUTER("6"),
	MENU_DELETE_COMPUTER("7"),
	MENU_SEARCH_COMPUTERS("8"),
	MENU_EXIT("exit"),
	MENU_DEFAULT("");
	
	private   String valeur ;
	
	Menu(String valeur) {
		this.valeur = valeur;
	
	}

	public String getValeur() {
		
		return this.valeur;
	}

	
	public static Menu getMenu(String valeur) {
		for (Menu menu : Menu.values()) {
			if (valeur.equals(menu.valeur)) {
				return menu;
			}
		}	
		return MENU_DEFAULT;
	}

	





	
	
	
	
	
}
