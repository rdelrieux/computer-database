package com.excilys.computerDatabase.front.binding.validateur;

import com.excilys.computerDatabase.front.binding.dto.CompanyDTO;
import com.excilys.computerDatabase.front.binding.exception.CompanyIdNotValidException;
import com.excilys.computerDatabase.front.binding.exception.NameNotValidException;
import com.excilys.computerDatabase.logger.LoggerCdb;

public class CompanyValidateur {

	private static CompanyValidateur instance;

	private CompanyValidateur() {

	}

	public static CompanyValidateur getInstance() {
		if (instance == null) {
			instance = new CompanyValidateur();
		}
		return instance;
	}

	public void validate(CompanyDTO companyDTO) {

		this.validateId(companyDTO.getId());
		this.validateName(companyDTO.getName());

	}

	private void validateId(String id) {
		if (!("".equals(id))) {
			
			try {
				int num = Integer.parseInt(id);

				if (num < 0) {
					throw new CompanyIdNotValidException("Company Id not valid : 1 >" + id);
				}

			} catch (Exception e) {
				LoggerCdb.logWarn(ComputerValidateur.class.getName(), e);
				throw new CompanyIdNotValidException("Company Id not valid : " + id);

			}

		}
	}

	private void validateName(String name) {
		if ( name == null || "null".equals(name) ) {
			throw new NameNotValidException("Name not valid : " + name);
		}
		
	}

}
