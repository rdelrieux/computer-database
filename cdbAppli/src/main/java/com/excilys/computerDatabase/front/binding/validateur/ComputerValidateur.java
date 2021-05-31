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
		
	}

	private void validateName(String name) {
		if (!(name != null && !name.isBlank() && !name.equals("null"))) {
			throw new NameNotValidException("Name not valid : " + name);
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

	

}
	
	
