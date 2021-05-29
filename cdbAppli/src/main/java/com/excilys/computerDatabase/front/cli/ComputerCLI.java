package com.excilys.computerDatabase.front.cli;

import java.util.List;

import com.excilys.computerDatabase.front.binding.dto.ComputerDTOOutput;


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

	public void showListComputer(List<ComputerDTOOutput> listComputer) {
		for (ComputerDTOOutput computer : listComputer) {
			System.out.println(computer);
		}
		System.out.println("");

	}
	public void showComputer(ComputerDTOOutput computer) {
		if (computer.getName() == "") {
			System.out.println(CLI.COMPUTER_NOT_FOUND_MESSAGE);
		}else {
			System.out.println(computer);	
		}
	}

	
}
