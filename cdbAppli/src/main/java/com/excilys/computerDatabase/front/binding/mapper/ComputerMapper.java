package com.excilys.computerDatabase.front.binding.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Computer.ComputerBuilder;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOOutput;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOOutput.ComputerDTOOutputBuilder;
import com.excilys.computerDatabase.front.binding.validateur.ComputerValidateur;

public class ComputerMapper {
	
	private static ComputerMapper instance;
	private CompanyMapper companyMapper;
	private ComputerValidateur computerValidateur;
	
	private ComputerMapper() {
		this.computerValidateur = ComputerValidateur.getInstance();
		this.companyMapper = CompanyMapper.getInstance();
		}

	public static ComputerMapper getInstance() {
		if (instance == null) {
			instance = new ComputerMapper();
		}
		return instance;
	}
	
	public Computer mapToComputer(ComputerDTOInput computerDTOInput) {
		
		this.computerValidateur.validate(computerDTOInput);
		
		ComputerBuilder builder =  new Computer.ComputerBuilder(0,computerDTOInput.getName());
				
				if (! "".equals(computerDTOInput.getIntroduced())) {
					builder.withIntroduced( LocalDate.parse(computerDTOInput.getIntroduced()) );
				}
				if (! "".equals(computerDTOInput.getDiscontinued())) {
					builder.withDiscontinued( LocalDate.parse(computerDTOInput.getDiscontinued()) );
				}
				builder.withCompany( this.companyMapper.mapToCompany(computerDTOInput.getCompanyDTO()) );
				
				return	builder.build();	
	}
	
	public ComputerDTOOutput  mapToComputerDTOOutput(Computer computer) {
		
		ComputerDTOOutputBuilder builder =  new ComputerDTOOutput.ComputerDTOOutputBuilder(""+computer.getId(),computer.getName()) ;
				
			if (computer.getIntroduced() != null) {
				builder.withIntroduced( ""+computer.getIntroduced() );
			}
			if (computer.getDiscontinued() != null) {
				builder.withDiscontinued( ""+computer.getDiscontinued() );
			}
			builder.withCompanyName( this.companyMapper.mapToCompanyDTO( computer.getCompany() ).getName() );
			
			return builder.build();	
	}
	
	public List<ComputerDTOOutput>  mapToListComputerDTOOutput(List<Computer> listComputer) {
		return listComputer.stream()
					.map(c -> this.mapToComputerDTOOutput(c)  )
					.collect(Collectors.toList());			
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
