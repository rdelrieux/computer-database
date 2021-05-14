package com.excilys.vue;

public abstract class  CLI {
	
	
	protected static final String BIENVENUE_MESSAGE = "Bienvenue sur CDB \n";
	protected static final String BYE_MESSAGE = "Bye \n";
	
	
	protected static final String INPUT_ERREUR = "Invalid input try again "; 
	protected static final String TO_MUCH_UNVALID_TRY = "Vous avez fait trop d'erreur \n"; 
	protected static final String NOT_VALID_IMPUT = "NOT_VALID_IMPUT"; 
	
	protected static final String MENU_SHOW_LIST_COMPANY = "1"; 
	protected static final String MENU_SHOW_COMPANY = "2"; 
	protected static final String MENU_SHOW_LIST_COMPUTER = "3"; 
	protected static final String MENU_SHOW_COMPUTER = "4"; 
	protected static final String MENU_ADD_COMPUTER = "5"; 
	protected static final String MENU_UPDATE_COMPUTER = "6"; 
	protected static final String MENU_DELETE_COMPUTER = "7"; 
	protected static final String MENU_EXIT = "exit"; 
	
	public static final String ENTER_ID_MESSAGE = "Entrez l'identifiant"; 
	public static final String ENTER_COMPUTER_NAME_MESSAGE = "Entrez le nom du Computer"; 
	protected static final String ENTER_DATE_INTRODUCED_MESSAGE = "Entrez la date Introduced (yyyy-mm-dd) "; 
	protected static final String ENTER_DATE_DISCONTINUD_MESSAGE = "Entrez la date Discontined (yyyy-mm-dd) "; 
	public static final String ENTER_COMPANY_NAME_MESSAGE = "Entrez le nom de la Company"; 

	
	protected static final String INVALID_DATE_MESSAGE = "Date Invalide"; 
	protected static final String INVALID_INPUT_MESSAGE = "Invalide input "; 


	
	protected static final String VALIDATION = "y";
	protected static final String NOT_VALIDATION = "n";
	protected static final String VALIDATION_NOT_VALID_MESSAGE = "validation not correct";
	protected static final String VALIDATION_MESSAGE = "Etes vous sur de vouloir continuer (y/n)";

	protected static final String CHANGER_NOM_MESSAGE = "Voulez vous changer le Name du Computer (y/n)";
	protected static final String CHANGER_DATE_INTRODUCED_MESSAGE = "Voulez vous changer la date Introduced (y/n)";
	protected static final String CHANGER_DATE_DISCONTINUD_MESSAGE = "Voulez vous changer la date Discontined (y/n)";
	protected static final String CHANGER_COMPANY_NAME_MESSAGE = "Voulez vous changer le Name de la Company (y/n)";

	public static final String COMPANY_NOT_FOUND_MESSAGE = "Cette company n'existe pas dans la base de donnee "; 
	public static final String COMPUTER_NOT_FOUND_MESSAGE = "Ce Computer n'existe pas dans la base de donnee "; 


	
	protected ScannerVerification sc = ScannerVerification.getInstance() ;
	
	
	
	

}
