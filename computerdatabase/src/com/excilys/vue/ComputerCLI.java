package com.excilys.vue;

import java.util.List;

import com.excilys.model.Computer;

public class ComputerCLI extends CLI{

	private static final String ENTER_COMPUTER_NAME_MESSAGE = "Entrez le nom"; 
	private static final String ENTER_COMPANY_NAME_MESSAGE = "Entrez le nom de la Company\nEntrez \"null\" si non renseigne"; 
	private static final String INVALID_DATE_MESSAGE = "Date Invalide"; 
	private static final String ENTER_DATE_INTRODUCED_MESSAGE = "Entrez la date Introduced (yyyy-mm-dd) \nEntrez \"null\" si non renseigne"; 
	private static final String ENTER_DATE_DISCONTINUD_MESSAGE = "Entrez la date Discontined (yyyy-mm-dd) \nEntrez \"null\" si non renseigne"; 

	
	private static final String VALIDATION = "y";
	private static final String NOT_VALIDATION = "n";
	private static final String VALIDATION_NOT_VALID_MESSAGE = "validation not correct";
	private static final String VALIDATION_MESSAGE = "Etes vous sur de vouloir continuer (y/n)";

	private static final String CHANGER_NOM_MESSAGE = "Voulez vous changer le Name du Computer (y/n)";
	private static final String CHANGER_DATE_INTRODUCED_MESSAGE = "Voulez vous changer la date Introduced (y/n)";
	private static final String CHANGER_DATE_DISCONTINUD_MESSAGE = "Voulez vous changer la date Discontined (y/n)";
	private static final String CHANGER_COMPANY_NAME_MESSAGE = "Voulez vous changer le Name de la Company (y/n)";

	
	private static ComputerCLI instance;

	private ComputerCLI() {

	}

	public static ComputerCLI getInstance() {
		if (instance == null) {
			instance = new ComputerCLI();
		}
		return instance;
	}

	public void showListComputer(List<Computer> listComputer) {
		for (Computer computer : listComputer) {
			System.out.println(computer.getId() + " \t " + computer.getName());
		}
		System.out.println("");

	}

	public boolean showComputerDetail(Computer computerDetail, int idTest) {
		if (computerDetail.getId() == 0) {
			System.out.println("il n'y a pas de computer numero " + idTest);
			return false;
		} else {
			String introduced = "pas definit";
			String discontinued = "pas definit";
			String companyName = computerDetail.getCompany().getName();
			if (computerDetail.getIntroduced() != null) {
				introduced = computerDetail.getIntroduced().toString();
			}
			if (computerDetail.getDiscontinued() != null) {
				discontinued = computerDetail.getDiscontinued().toString();
			}
			if (computerDetail.getCompany().getId() == 0) {
				companyName = "pas definit";
			}
			System.out.println(computerDetail.getId() + "- " + computerDetail.getName() + "\n " + introduced + "\n "
					+ discontinued + "\n " + companyName);
		}
		return true;
	}

	public String[] choixParametreAddComputer() {
		
		String[] res = new String [4];
		res[0] = this.choixName();
		res[1] = this.choixDate(ENTER_DATE_INTRODUCED_MESSAGE);
		res[2] = this.choixDate(ENTER_DATE_DISCONTINUD_MESSAGE);
		res[3] = this.choixCompany();	
		return res;
	}

	public boolean messageVerificationAction(String[] message, String action) {
		System.out.println(action );
		System.out.println(message[0]+","+message[1]+","+message[2]+","+message[3]);
		System.out.println(VALIDATION_MESSAGE);
		
		String validation = this.sc.typeString();
		while ( ! (validation.equals(VALIDATION) || validation.equals(NOT_VALIDATION) ) ){
			System.out.println(VALIDATION_NOT_VALID_MESSAGE);
			System.out.println(action);
			System.out.println(message[0]+","+message[1]+","+message[2]+","+message[3]);
			System.out.println(VALIDATION_MESSAGE);
			validation = this.sc.typeString();
			
		}
		if (validation.equals(VALIDATION)) {
			return true;
		}
	
		return false;
	}

	
	private String choixCompany() {
		System.out.println(ENTER_COMPANY_NAME_MESSAGE);
		String res = this.sc.typeString();
		return res;
	}

	private String choixDate(String message) {
		System.out.println(message);
		String date = this.sc.typeString();
		while (! sc.isDate(date)) {
			System.out.println(INVALID_DATE_MESSAGE);
			System.out.println(message);
			date=  this.sc.typeString();
		}
		
		if (message.equals(ENTER_DATE_INTRODUCED_MESSAGE)) {
			return "2010-01-01";
		}else {
			return "2011-01-01";
		}
		//return date;
	}

	private String choixName() {
		System.out.println(ENTER_COMPUTER_NAME_MESSAGE);
		String res = this.sc.typeString();
		return res;
	}

	public String[] choixParametreUpdateComputer() {
		String[] res = {CLI.UNCHANGED, CLI.UNCHANGED, CLI.UNCHANGED, CLI.UNCHANGED};
		
		if (needUpdate(CHANGER_NOM_MESSAGE)) {
			res[0] = this.choixName();
		}
		if (needUpdate(CHANGER_DATE_INTRODUCED_MESSAGE)) {
			res[1] = this.choixDate(ENTER_DATE_INTRODUCED_MESSAGE);
		}
		if (needUpdate(CHANGER_DATE_DISCONTINUD_MESSAGE)) {
			res[2] = this.choixDate(ENTER_DATE_DISCONTINUD_MESSAGE);
		}
		if (needUpdate(CHANGER_COMPANY_NAME_MESSAGE)) {
			res[3] = this.choixCompany();	
		}
		
		return res;
	}
	
	private boolean needUpdate(String message) {
		System.out.println(message);
		String validation = this.sc.typeString();
		while ( ! (validation.equals(VALIDATION) || validation.equals(NOT_VALIDATION) ) ){
			System.out.println(VALIDATION_NOT_VALID_MESSAGE);
			System.out.println(message );
			validation = this.sc.typeString();
		}
		if (validation.equals(VALIDATION)){
			return true;
		}
		return false;
	}
}
