package com.excilys.computerDatabase.front.cli;

import java.util.List;


import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.front.binding.dto.ComputerDTOOutput;

@Component
public class ComputerCLI extends CLI{
	
	public void showListComputer(List<ComputerDTOOutput> listComputer) {
		listComputer.stream().forEach(c -> System.out.println(c));
	}
	
	public void showComputer(ComputerDTOOutput computer) {
		if ("".equals(computer.getName()) ) {
			System.out.println(CLI.COMPUTER_NOT_FOUND_MESSAGE);
		}else {
			System.out.println(computer);	
		}
	}

	
}
