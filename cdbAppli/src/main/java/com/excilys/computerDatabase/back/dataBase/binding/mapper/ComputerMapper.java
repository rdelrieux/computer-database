package com.excilys.computerDatabase.back.dataBase.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.back.dataBase.binding.dto.CompanyDTOOutput;
import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOOutput;
import com.excilys.computerDatabase.back.dataBase.binding.dto.ComputerDTOOutput.ComputerDTOOutputBuilder;
import com.excilys.computerDatabase.back.dataBase.exception.ComputerNotFoundException;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Computer.ComputerBuilder;
import com.excilys.computerDatabase.logger.LoggerCdb;

@Component("computerMapperDAO")
public class ComputerMapper {

	private static final String COLONNE_ID = "id";
	private static final String COLONNE_NAME = "name";
	private static final String COLONNE_DATE_INTRODUCED = "introduced";
	private static final String COLONNE_DATE_DISCONTINUED = "discontinued";
	private static final String COLONNE_COMPANY_ID = "company_id";
	private static final String COLONNE_COMPANY_NAME = "company.name";
	

	public ComputerDTOOutput mapToComputerDTOOutput(ResultSet result) {

		try {
			ComputerDTOOutputBuilder computerDTOOutput = new ComputerDTOOutput.ComputerDTOOutputBuilder(
					result.getString(COLONNE_ID), result.getString(COLONNE_NAME));

			if (result.getDate(COLONNE_DATE_INTRODUCED) != null) {
				computerDTOOutput.withIntroduced("" + result.getDate(COLONNE_DATE_INTRODUCED));
			}

			if (result.getDate(COLONNE_DATE_DISCONTINUED) != null) {
				computerDTOOutput.withDiscontinued("" + result.getDate(COLONNE_DATE_DISCONTINUED));
			}

			if (result.getString(COLONNE_COMPANY_ID) != null) {
				computerDTOOutput.withCompany(new CompanyDTOOutput(result.getString(COLONNE_COMPANY_ID),
						result.getString(COLONNE_COMPANY_NAME)));
			}

			return computerDTOOutput.build();

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerMapper.class.getName(), e);
			throw new ComputerNotFoundException();

		}

	}

	public List<ComputerDTOOutput> mapToListComputerDTOOutput(ResultSet result) {
		ArrayList<ComputerDTOOutput> res = new ArrayList<>();

		try {
			while (result.next()) {
				res.add(this.mapToComputerDTOOutput(result));
			}

		} catch (SQLException e) {
			LoggerCdb.logError(ComputerMapper.class.getName(), e);

		}
		return res;
	}

	public Computer mapToComputer(ComputerDTOOutput computerDTOOutput) {
		
		ComputerBuilder computerBuilder = new Computer.ComputerBuilder(Integer.valueOf(computerDTOOutput.getId()),
				computerDTOOutput.getName());

		if ( ! "".equals( computerDTOOutput.getIntroduced())) {
			computerBuilder.withIntroduced(LocalDate.parse(computerDTOOutput.getIntroduced()));
		}
		if ( ! "".equals( computerDTOOutput.getDiscontinued())) {
			computerBuilder.withDiscontinued(LocalDate.parse(computerDTOOutput.getDiscontinued()));
		}
		if ( ! "".equals( computerDTOOutput.getCompanyDTOOutput().getId() )) {
			computerBuilder.withCompany(new Company(Integer.valueOf(computerDTOOutput.getCompanyDTOOutput().getId()),
					computerDTOOutput.getCompanyDTOOutput().getName()));
		}

		return computerBuilder.build();
	}

	public List<Computer> maptoListComputer(List<ComputerDTOOutput> listComputerDTOOutput) {
		return listComputerDTOOutput.stream()
			.map(cOut -> this.mapToComputer(cOut))
			.collect(Collectors.toList());
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
