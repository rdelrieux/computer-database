package com.excilys.vue;

import java.util.List;


import com.excilys.model.Computer;

public class ComputerCLI extends CLI{


	/*
	private static final String VALIDATION = "y";
	private static final String NOT_VALIDATION = "n";
	private static final String VALIDATION_NOT_VALID_MESSAGE = "validation not correct";
	private static final String VALIDATION_MESSAGE = "Etes vous sur de vouloir continuer (y/n)";
	*/
	
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
			System.out.println(computer);
		}
		System.out.println("");

	}
	public void showComputer(Computer computer) {
		if (computer == null) {
			System.out.println(CLI.COMPUTER_NOT_FOUND_MESSAGE);
		}else {
			System.out.println(computer);	
		}
	}

	


	/*
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
	 */
	


	
}
