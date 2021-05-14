package com.excilys.vue;

public abstract class  CLI {
	
	public static final String UNCHANGED = "unchanged";
	
	protected static final String BIENVENUE_MESSAGE = "Bienvenue sur CDB \n";
	protected static final String BYE_MESSAGE = "Bye \n";
	
	
	protected static final String IMPUT_ERREUR = "Invalid imput try again "; 
	protected static final String TO_MUCH_UNVALID_TRY = "Vous avez fait trop d'erreur \n"; 
	protected static final String NOT_VALID_IMPUT = "NOT_VALID_IMPUT"; 
	
	protected static final String MENU_SHOW_LIST_COMPANY = "1"; 
	protected static final String MENU_SHOW_LIST_COMPUTER = "2"; 
	protected static final String MENU_SHOW_COMPUTER = "3"; 
	protected static final String MENU_ADD_COMPUTER = "4"; 
	protected static final String MENU_UPDATE_COMPUTER = "5"; 
	protected static final String MENU_DELETE_COMPUTER = "6"; 
	protected static final String MENU_EXIT = "exit"; 
	

	
	protected ScannerVerification sc = ScannerVerification.getInstance() ;
	
	
	
	

}
