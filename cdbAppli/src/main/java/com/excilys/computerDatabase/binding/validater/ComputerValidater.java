package com.excilys.computerDatabase.binding.validater;

import java.time.LocalDate;

import com.excilys.computerDatabase.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.exception.NameNotValidException;
import com.excilys.computerDatabase.logger.LoggerCdb;

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

	public void validate(ComputerDTOInput computerDTOInput)  {
		
			this.validateName(computerDTOInput.getName());
			
		
		/*
		return  
				&& this.validateDate(computerDTOInput.getIntroduced())
				&& this.validateDate(computerDTOInput.getDiscontinued())
				&& this.validateDateInterval(computerDTOInput.getIntroduced(), computerDTOInput.getDiscontinued())
				&& this.validateCompanyId(computerDTOInput.getCompanyId())
				;
*/
	}

	private boolean validateCompanyId(String id) {
		//System.out.println("Logg Service : introduced >= discontinued valid ");

		if (! (id.equals("") ||  this.validateId(id))) {
			System.out.println("Logg erreur : company not valid");
		}
		return id.equals("") ||  this.validateId(id);

	}

	private boolean validateDateInterval(String introduced, String discontinued) {
		//System.out.println("Logg Service : discontinued valid ");
		if ( introduced.equals("") || discontinued.equals("")) {

			return true;
		}
		try {
			if (! LocalDate.parse(introduced).isBefore(LocalDate.parse(discontinued))){
				System.out.println("Logg erreur : Date Interval not valid" );
			}
			return LocalDate.parse(introduced).isBefore(LocalDate.parse(discontinued));

		} catch (Exception e) {
			System.out.println("Logg erreur : Date not valid" );
			return false;
		}

	}

	private boolean validateDate(String date) {
		//System.out.println("Logg Service : name / introduced valid ");
		if (date.equals("")) {
			
			return true;
		}
		try {
			LocalDate.parse(date);
			return true;
		} catch (Exception e) {
			System.out.println("Logg erreur : Date not valid" );
			return false;
		}

	}

	private void validateName(String name) throws NameNotValidException{
		if ( ! ( name != null && !name.isBlank() && !name.equals("null")) ) {
			throw new NameNotValidException("Name not valid : " + name);
		}
	}

	private boolean validateId(String id) {
		
		try {
			int num = Integer.parseInt(id);
			
			if (num <=0) {
				System.out.println("Logg erreur : Id <= 0" );
			}
			
			return num >0;

		} catch (Exception e) {
			System.out.println("Logg erreur : Id not valid" );
			return false;
		}

	}

}
