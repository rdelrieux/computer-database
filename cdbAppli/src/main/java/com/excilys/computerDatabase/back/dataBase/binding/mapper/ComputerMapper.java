package com.excilys.computerDatabase.back.dataBase.binding.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerEntity;
import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerEntityAdd;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Computer.ComputerBuilder;

@Component
public class ComputerMapper {

	private CompanyMapper companyMapper;

	public ComputerMapper(CompanyMapper companyMapper) {
		super();
		this.companyMapper = companyMapper;
	}

	public Computer mapToComputer(ComputerEntity computerEntity) {
		ComputerBuilder computerBuilder = new Computer.ComputerBuilder(computerEntity.getId(), computerEntity.getName())
				.withIntroduced(computerEntity.getIntroduced()).withDiscontinued(computerEntity.getDiscontinued());

		if (computerEntity.getCompany() != null) {
			computerBuilder.withCompany(this.companyMapper.mapToCompany(computerEntity.getCompany()));
		}
		
		return computerBuilder.build();
	}

	public List<Computer> mapToComputer(List<ComputerEntity> listComputerEntity) {
		return listComputerEntity.stream().map(c -> this.mapToComputer(c)).collect(Collectors.toList());
	}

	public ComputerEntityAdd mapToComputerEntity(Computer computer) {
		ComputerEntityAdd computerAdd = new ComputerEntityAdd();
		computerAdd.setName(computer.getName());
		computerAdd.setIntroduced(computer.getIntroduced());
		computerAdd.setDiscontinued(computer.getDiscontinued());
		
		if (computer.getCompany() != null) {
			computerAdd.setCompanyId(computer.getCompany().getId());
		}

		return computerAdd;
	}

	

}
