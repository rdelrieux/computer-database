package com.excilys.computerDatabase.front.binding.validateur;

import java.time.LocalDate;

import com.excilys.computerDatabase.front.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.front.binding.exception.CompanyIdNotValidException;
import com.excilys.computerDatabase.front.binding.exception.DateFormaNotValidException;
import com.excilys.computerDatabase.front.binding.exception.DateIntervalNotValidException;
import com.excilys.computerDatabase.front.binding.exception.NameNotValidException;
import com.excilys.computerDatabase.logger.LoggerCdb;

public class ComputerValidateur {
	
	private static ComputerValidateur instance;

	private ComputerValidateur() {

	}

	public static ComputerValidateur getInstance() {
		if (instance == null) {
			instance = new ComputerValidateur();
		}
		return instance;
	}

	public void validate(ComputerDTOInput computerDTOInput) {

		this.validateName(computerDTOInput.getName());
		this.validateDate(computerDTOInput.getIntroduced());
		this.validateDate(computerDTOInput.getDiscontinued());
		this.validateDateInterval(computerDTOInput.getIntroduced(), computerDTOInput.getDiscontinued());
		this.validateCompanyId(computerDTOInput.getCompanyId()) ;
		
	}

	private void validateCompanyId(String id) {
		if (!("".equals(id) ) ) {
			this.validateId(id);
		}
	}

	private void validateDateInterval(String introduced, String discontinued) {
		
	
			if ( ! ("".equals(introduced) || "".equals(discontinued)) ) {
				if (!LocalDate.parse(introduced).isBefore(LocalDate.parse(discontinued))) {
					throw new DateIntervalNotValidException("Date Interval not valid : " + introduced+ ">"+discontinued);
				}
			}


	}

	private void validateDate(String date) {
		try {
			if (!"".equals(date)) {
				LocalDate.parse(date);
			}

		} catch (Exception e) {
			throw new DateFormaNotValidException("Date Forma not valid : " + date);
		}

	}

	private void validateName(String name) {
		if (!(name != null && !name.isBlank() && !name.equals("null"))) {
			throw new NameNotValidException("Name not valid : " + name);
		}
	}

	private void validateId(String id) {

		try {
			int num = Integer.parseInt(id);

			if (num <= 0) {
				throw new CompanyIdNotValidException("Company Id not valid : " + id);
			}
			

		} catch (Exception e) {
			LoggerCdb.logWarn(ComputerValidateur.class.getName(), e);
			throw new CompanyIdNotValidException("Company Id not valid : " + id);
			
		}

	}

}
	
	
