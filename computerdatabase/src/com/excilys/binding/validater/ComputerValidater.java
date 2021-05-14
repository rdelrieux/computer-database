package com.excilys.binding.validater;

import java.time.LocalDate;

import com.excilys.binding.dto.ComputerDTOInput;
import com.excilys.binding.dto.ComputerDTOSQL;

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

	public boolean validate(ComputerDTOInput computerDTOInput) {
		return  this.validateName(computerDTOInput.getName())
				&& this.validateDate(computerDTOInput.getIntroduced()) && this.validateDate(computerDTOInput.getDiscontinued())
				&& this.validateDateInterval(computerDTOInput.getIntroduced(), computerDTOInput.getDiscontinued())
				;

	}

	private boolean validateCompany(String id, String name) {
		//System.out.println("Logg Service : introduced >= discontinued valid ");

		return name.equals("") || this.validateId(id) && this.validateName(name);

	}

	private boolean validateDateInterval(String introduced, String discontinued) {
		//System.out.println("Logg Service : discontinued valid ");
		if ( introduced.equals("") || discontinued.equals("")) {

			return true;
		}
		try {
			return LocalDate.parse(introduced).isBefore(LocalDate.parse(discontinued));

		} catch (Exception e) {
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
			return false;
		}

	}

	private boolean validateName(String name) {
		//System.out.println("Logg Service : Id valid ");

		return name != null && !name.isBlank() && !name.equals("null");

	}

	private boolean validateId(String id) {
		
		try {
			int num = Integer.parseInt(id);
			
			return num >0;

		} catch (Exception e) {
			
			return false;
		}

	}

}
