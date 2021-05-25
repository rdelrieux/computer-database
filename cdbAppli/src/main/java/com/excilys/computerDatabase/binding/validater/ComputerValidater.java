package com.excilys.computerDatabase.binding.validater;

import java.time.LocalDate;

import com.excilys.computerDatabase.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.exception.CompanyIdNotValidException;
import com.excilys.computerDatabase.exception.DateFormaNotValidException;
import com.excilys.computerDatabase.exception.DateIntervalNotValidException;
import com.excilys.computerDatabase.exception.NameNotValidException;
import com.excilys.computerDatabase.logger.LoggerCdb;
import com.excilys.computerDatabase.persistence.dao.implement.ComputerDAO;

public class ComputerValidater {

	private static ComputerValidater instance;

	private ComputerValidater() {

	}

	public static ComputerValidater getInstance() {
		if (instance == null) {
			instance = new ComputerValidater();
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
		if (!(id.equals("") ) ) {
			this.validateId(id);
		}
	}

	private void validateDateInterval(String introduced, String discontinued) {
		
	
			if ( ! (introduced.equals("") || discontinued.equals("")) ) {
				if (!LocalDate.parse(introduced).isBefore(LocalDate.parse(discontinued))) {
					throw new DateIntervalNotValidException("Date Interval not valid : " + introduced+ ">"+discontinued);
				}
			}


	}

	private void validateDate(String date) {
		try {
			if (!date.equals("")) {
				LocalDate.parse(date);
			}

		} catch (Exception e) {
			throw new DateFormaNotValidException("Date Forma not valid : " + date);
		}

	}

	private void validateName(String name) throws NameNotValidException {
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
			LoggerCdb.logWarn(ComputerValidater.class.getName(), e);
			throw new CompanyIdNotValidException("Company Id not valid : " + id);
			
		}

	}

}
