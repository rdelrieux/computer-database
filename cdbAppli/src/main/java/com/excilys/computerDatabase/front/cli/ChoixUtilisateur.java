package com.excilys.computerDatabase.front.cli;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOUpdate.ComputerDTOUpdateBuilder;

@Component
public class ChoixUtilisateur extends CLI{

	private Scanner sc = new Scanner(System.in);

	
	public String choixName(String message) {
		System.out.println (message);
		return sc.nextLine();
	}
	public String choixNameNotEmpty(String message) {
		System.out.println (message);
		String name = sc.nextLine();
		while ( name.isEmpty()) {
			System.out.println(CLI.INVALID_INPUT_MESSAGE);
			System.out.println (message);
			name = sc.nextLine();
		}
		return name;
	}
	
	public String choixId(String message) {
		System.out.println (message);
		return sc.nextLine();
	}

	private String choixDate(String message) {
		System.out.println(message);
		String date = this.sc.nextLine();		
		return date;
	}
	
	
	public ComputerDTOAdd choixParametreAddComputer() {
		return new ComputerDTOAdd.ComputerDTOAddBuilder(this.choixNameNotEmpty(CLI.ENTER_COMPUTER_NAME_MESSAGE))
	
		
			.withIntroduced(this.choixDate(CLI.ENTER_DATE_INTRODUCED_MESSAGE))
		
			.withDiscontinued(this.choixDate(CLI.ENTER_DATE_DISCONTINUED_MESSAGE))
		
			.withCompanyId( ""+this.choixId(CLI.ENTER_ID_MESSAGE) )	
		
		 	.build();
	}

	public ComputerDTOUpdate choixParametreUpdateComputer(Computer computer) {
		ComputerDTOUpdateBuilder ComputerDTOUpdateBuilder = new ComputerDTOUpdate.ComputerDTOUpdateBuilder(""+computer.getId() , computer.getName());
				
		if (needUpdate(CHANGER_NOM_MESSAGE)) {
			ComputerDTOUpdateBuilder = new ComputerDTOUpdate.ComputerDTOUpdateBuilder(""+computer.getId(),this.choixNameNotEmpty(CLI.ENTER_COMPUTER_NAME_MESSAGE));
		}
		if (needUpdate(CHANGER_DATE_INTRODUCED_MESSAGE)) {
			ComputerDTOUpdateBuilder.withIntroduced(this.choixDate(CLI.ENTER_DATE_INTRODUCED_MESSAGE));
		}else {
			ComputerDTOUpdateBuilder.withIntroduced(computer.getIntroduced()== null ? "" : computer.getIntroduced().toString());
		}
		if (needUpdate(CHANGER_DATE_DISCONTINUD_MESSAGE)) {
			ComputerDTOUpdateBuilder.withDiscontinued(this.choixDate(CLI.ENTER_DATE_DISCONTINUED_MESSAGE));
		}else {
			ComputerDTOUpdateBuilder.withDiscontinued(computer.getDiscontinued()== null ? "" : computer.getDiscontinued().toString());
		}
		if (needUpdate(CHANGER_COMPANY_NAME_MESSAGE)) {
			ComputerDTOUpdateBuilder.withCompanyId (""+this.choixId(CLI.ENTER_ID_MESSAGE) );	
		}else {
			ComputerDTOUpdateBuilder.withCompanyId( computer.getCompany() == null ?  "" : ""+computer.getCompany().getId());	
		}
		return 	ComputerDTOUpdateBuilder.build();
	}
	
	private boolean needUpdate(String message) {
		System.out.println(message);
		String validation = this.sc.nextLine();
		while ( ! ( validation.equals(VALIDATION) || validation.equals(NOT_VALIDATION) ) ){
			System.out.println(VALIDATION_NOT_VALID_MESSAGE);
			System.out.println(message );
			validation = this.sc.nextLine();
		}
		if (validation.equals(VALIDATION)){
			return true;
		}
		return false;
	}
	
	public boolean messageVerificationAction() {
		System.out.println(VALIDATION_MESSAGE);
		
		String validation = this.sc.nextLine();
		while ( ! (validation.equals(VALIDATION) || validation.equals(NOT_VALIDATION) ) ){
			System.out.println(VALIDATION_NOT_VALID_MESSAGE);
			System.out.println(VALIDATION_MESSAGE);
			validation = this.sc.nextLine();
		}
		if (validation.equals(VALIDATION)) {
			return true;
		}
	
		return false;
	}
	
}
