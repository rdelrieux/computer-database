package com.excilys.computerDatabase.front.binding.validateur;

import java.time.LocalDate;

import com.excilys.computerDatabase.front.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.front.binding.exception.IdNotValidException;
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

	public void validate(ComputerDTOAdd computerDTOAdd) {
		
		this.validateName(computerDTOAdd.getName());
		this.validateDate(computerDTOAdd.getIntroduced());
		this.validateDate(computerDTOAdd.getDiscontinued());
		this.validateDateInterval(computerDTOAdd.getIntroduced(), computerDTOAdd.getDiscontinued());
		this.validateCompanyId(computerDTOAdd.getCompanyId());
	}

	public void validate(ComputerDTOUpdate computerDTOUpdate) {
		
		this.validateId(computerDTOUpdate.getId());
		this.validateName(computerDTOUpdate.getName());
		this.validateDate(computerDTOUpdate.getIntroduced());
		this.validateDate(computerDTOUpdate.getDiscontinued());
		this.validateDateInterval(computerDTOUpdate.getIntroduced(), computerDTOUpdate.getDiscontinued());
		this.validateCompanyId(computerDTOUpdate.getCompanyId());
		
	}
	
	
	private void validateId(String id) {
		try {
			int num = Integer.parseInt(id);

			if (num <= 0) {
				throw new IdNotValidException("Id not valid :" + id+ "negatif or =0");
			}

		} catch (Exception e) {
			LoggerCdb.logWarn(ComputerValidateur.class.getName(), e);
			throw new IdNotValidException("Id not valid : " + id);

		}
		
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

	private void validateCompanyId(String id) {
		if (!("".equals(id))) {
			
			validateId( id);

		}
	}

	
	

}
	
	
