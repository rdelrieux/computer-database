package com.excilys.computerDatabase.front.cli;

import java.util.List;


import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.front.binding.dto.ComputerEntity;

@Component
public class ComputerCLI extends CLI{
	
	public void showListComputer(List<ComputerEntity> listComputer) {
		listComputer.stream().forEach(c -> System.out.println(c));
	}
	
	public void showComputer(ComputerEntity computer) {
		if ("".equals(computer.getName()) ) {
			System.out.println(CLI.COMPUTER_NOT_FOUND_MESSAGE);
		}else {
			System.out.println(computer);	
		}
	}

	
}
