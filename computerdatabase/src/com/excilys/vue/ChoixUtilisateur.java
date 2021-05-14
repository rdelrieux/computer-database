package com.excilys.vue;

import com.excilys.binding.builder.ComputerDTOInputBuilder;
import com.excilys.binding.dto.ComputerDTOInput;
import com.excilys.model.Computer;

public class ChoixUtilisateur extends CLI{
	
	private static ChoixUtilisateur instance ;


	private ChoixUtilisateur() {
		
	}
	
	public static ChoixUtilisateur getInstance()  {
		if (instance == null) {
			instance = new ChoixUtilisateur();
		}
		return instance;
	}

	public String choixName(String message) {
		System.out.println (message);
		return sc.typeString();
	}
	public String choixNameNotEmpty(String message) {
		System.out.println (message);
		String name = sc.typeString();
		while ( name.isEmpty()) {
			System.out.println(CLI.INVALID_INPUT_MESSAGE);
			System.out.println (message);
			name = sc.typeString();
		}
		return name;
	}
	
	public int choixId(String message) {
		System.out.println (message);
		return sc.typeIdValid();
	}

	private String choixDate(String message) {
		System.out.println(message);
		String date = this.sc.typeString();
		while (! sc.isDate(date)) {
			System.out.println(INVALID_DATE_MESSAGE);
			System.out.println(message);
			date= this.sc.typeString();
		}
		
		return date;
	}
	
	
	public ComputerDTOInput choixParametreAddComputer() {
		return new ComputerDTOInputBuilder()
		
			.setName( this.choixNameNotEmpty(CLI.ENTER_COMPUTER_NAME_MESSAGE))
		
			.setIntroduced(this.choixDate(CLI.ENTER_DATE_INTRODUCED_MESSAGE))
		
			.setDiscontinued(this.choixDate(CLI.ENTER_DATE_INTRODUCED_MESSAGE))
		
			.setCompanyName( this.choixName(CLI.ENTER_COMPANY_NAME_MESSAGE) )	
		
		 	.build();
	}

	public ComputerDTOInput choixParametreUpdateComputer(Computer computer) {
		ComputerDTOInputBuilder ComputerDTOInputBuilder = new ComputerDTOInputBuilder();
		
		ComputerDTOInputBuilder.setId( ""+computer.getId() );
		
		if (needUpdate(CHANGER_NOM_MESSAGE)) {
			ComputerDTOInputBuilder.setName( this.choixNameNotEmpty(CLI.ENTER_COMPUTER_NAME_MESSAGE) );
		}else {
			ComputerDTOInputBuilder.setName(computer.getName());
		}
		if (needUpdate(CHANGER_DATE_INTRODUCED_MESSAGE)) {
			ComputerDTOInputBuilder.setIntroduced(this.choixDate(CLI.ENTER_DATE_INTRODUCED_MESSAGE));
		}else {
			ComputerDTOInputBuilder.setIntroduced(computer.getIntroduced()== null ? "" : computer.getIntroduced().toString());
		}
		if (needUpdate(CHANGER_DATE_DISCONTINUD_MESSAGE)) {
			ComputerDTOInputBuilder.setDiscontinued(this.choixDate(CLI.ENTER_DATE_DISCONTINUD_MESSAGE));
		}else {
			ComputerDTOInputBuilder.setDiscontinued(computer.getDiscontinued()== null ? "" : computer.getDiscontinued().toString());

		}
		if (needUpdate(CHANGER_COMPANY_NAME_MESSAGE)) {
			ComputerDTOInputBuilder.setCompanyName( this.choixName(CLI.ENTER_COMPANY_NAME_MESSAGE) );	
		}else {
			ComputerDTOInputBuilder.setCompanyName( computer.getCompany() == null ?  "" : computer.getCompany().getName());	
		}
		return 	ComputerDTOInputBuilder.build();
	}
	
	private boolean needUpdate(String message) {
		System.out.println(message);
		String validation = this.sc.typeString();
		while ( ! ( validation.equals(VALIDATION) || validation.equals(NOT_VALIDATION) ) ){
			System.out.println(VALIDATION_NOT_VALID_MESSAGE);
			System.out.println(message );
			validation = this.sc.typeString();
		}
		if (validation.equals(VALIDATION)){
			return true;
		}
		return false;
	}
	
	public boolean messageVerificationAction() {
		System.out.println(VALIDATION_MESSAGE);
		
		String validation = this.sc.typeString();
		while ( ! (validation.equals(VALIDATION) || validation.equals(NOT_VALIDATION) ) ){
			System.out.println(VALIDATION_NOT_VALID_MESSAGE);
			System.out.println(VALIDATION_MESSAGE);
			validation = this.sc.typeString();
		}
		if (validation.equals(VALIDATION)) {
			return true;
		}
	
		return false;
	}
	
}
