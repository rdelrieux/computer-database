package com.excilys.computerDatabase.front.binding.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Computer.ComputerBuilder;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOOutput;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOOutput.ComputerDTOOutputBuilder;

@Component("computerMapperCtr")
public class ComputerMapper {

	
	@Qualifier("companyMapperCtr")
	private CompanyMapper companyMapper;
	

	
	public ComputerMapper(CompanyMapper companyMapper) {
		this.companyMapper = companyMapper;
	}

	public ComputerDTOOutput mapToComputerDTOOutput(Computer computer) {

		ComputerDTOOutputBuilder builder = new ComputerDTOOutput.ComputerDTOOutputBuilder("" + computer.getId(),
				computer.getName());

		if (computer.getIntroduced() != null) {
			builder.withIntroduced("" + computer.getIntroduced());
		}
		if (computer.getDiscontinued() != null) {
			builder.withDiscontinued("" + computer.getDiscontinued());
		}
		builder.withCompanyName(this.companyMapper.mapToCompanyDTO(computer.getCompany()).getName());

		return builder.build();
	}

	public List<ComputerDTOOutput> mapToListComputerDTOOutput(List<Computer> listComputer) {
		return listComputer.stream().map(c -> this.mapToComputerDTOOutput(c)).collect(Collectors.toList());
	}

	public Computer mapToComputer(ComputerDTOAdd computerDTOAdd) {


		ComputerBuilder builder = new Computer.ComputerBuilder(0, computerDTOAdd.getName());

		if (!"".equals(computerDTOAdd.getIntroduced())) {
			builder.withIntroduced(LocalDate.parse(computerDTOAdd.getIntroduced()));
		}
		if (!"".equals(computerDTOAdd.getDiscontinued())) {
			builder.withDiscontinued(LocalDate.parse(computerDTOAdd.getDiscontinued()));
		}
		if (!"".equals(computerDTOAdd.getCompanyId())) {
			builder.withCompany(new Company(Integer.parseInt(computerDTOAdd.getCompanyId()), "addComputer"));
		}

		return builder.build();
	}

	public Computer mapToComputer(ComputerDTOUpdate computerDTOUpdate) {


		ComputerBuilder builder = new Computer.ComputerBuilder(Integer.parseInt(computerDTOUpdate.getId()),
				computerDTOUpdate.getName());

		if (!"".equals(computerDTOUpdate.getIntroduced())) {
			builder.withIntroduced(LocalDate.parse(computerDTOUpdate.getIntroduced()));
		}
		if (!"".equals(computerDTOUpdate.getDiscontinued())) {
			builder.withDiscontinued(LocalDate.parse(computerDTOUpdate.getDiscontinued()));
		}
		if (!"".equals(computerDTOUpdate.getCompanyId())) {
			builder.withCompany(new Company(Integer.parseInt(computerDTOUpdate.getCompanyId()), "updateComputer"));
		}

		return builder.build();
	}

	public ComputerDTOUpdate mapToComputerDTOUpdate(Computer computer) {
		ComputerDTOUpdate computerDTOUpdate = new ComputerDTOUpdate("" + computer.getId());

		computerDTOUpdate.setName(computer.getName());

		
		if (computer.getIntroduced() != null) {
			computerDTOUpdate.setIntroduced("" + computer.getIntroduced());
		}
		if (computer.getDiscontinued() != null) {
			computerDTOUpdate.setDiscontinued("" + computer.getDiscontinued());
		}
		if (computer.getCompany() != null) {
			computerDTOUpdate.setCompanyId("" + computer.getCompany().getId());
		}
		return computerDTOUpdate;
	}

	
	

}
