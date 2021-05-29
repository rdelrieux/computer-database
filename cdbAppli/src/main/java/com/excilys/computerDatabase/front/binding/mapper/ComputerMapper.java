package com.excilys.computerDatabase.front.binding.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOOutput;
import com.excilys.computerDatabase.front.binding.validateur.ComputerValidateur;

public class ComputerMapper {
	
	private static ComputerMapper instance;
	private ComputerValidateur computerValidateur;
	
	private ComputerMapper() {
		this.computerValidateur = ComputerValidateur.getInstance();
		}

	public static ComputerMapper getInstance() {
		if (instance == null) {
			instance = new ComputerMapper();
		}
		return instance;
	}
	
	public Computer mapToComputer(ComputerDTOInput computerDTOInput) {
		
		this.computerValidateur.validate(computerDTOInput);
		
		return new Computer.ComputerBuilder(0,computerDTOInput.getName())
				
				.withIntroduced(computerDTOInput.getIntroduced() == "" ? null : LocalDate.parse(computerDTOInput.getIntroduced()))
				
				.withDiscontinued(computerDTOInput.getDiscontinued() == "" ? null :LocalDate.parse(computerDTOInput.getDiscontinued()))
				
				.withCompany(computerDTOInput.getCompanyId() == "" ? null : new Company (
						Integer.parseInt(computerDTOInput.getCompanyId()),""
						))
				
				.build();	
	}
	
	public ComputerDTOOutput  mapToComputerDTOOutput(Computer computer) {
		
		return new ComputerDTOOutput.ComputerDTOOutputBuilder(computer.getName())
				
				.withIntroduced(computer.getIntroduced() == null ? "" : ""+computer.getIntroduced())
				
				.withDiscontinued(computer.getDiscontinued() == null ? "" : ""+computer.getDiscontinued())
				
				.withCompanyName(computer.getCompany() == null ? "" : computer.getCompany().getName())
				
				.build();	
	}
	
	public List<ComputerDTOOutput>  mapToListComputerDTOOutput(List<Computer> listComputer) {
		return listComputer.stream()
					.map(c -> this.mapToComputerDTOOutput(c)  )
					.collect(Collectors.toList());			
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
