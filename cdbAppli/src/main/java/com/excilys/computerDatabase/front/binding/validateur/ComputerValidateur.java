package com.excilys.computerDatabase.front.binding.validateur;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.front.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.front.binding.dto.ComputerDTOUpdate;
import com.excilys.computerDatabase.front.binding.exception.IdNotValidException;
import com.excilys.computerDatabase.front.binding.exception.DateFormatNotValidException;
import com.excilys.computerDatabase.front.binding.exception.DateIntervalNotValidException;
import com.excilys.computerDatabase.front.binding.exception.NameNotValidException;
import com.excilys.computerDatabase.front.binding.exception.ValidateurDTOException;
import com.excilys.computerDatabase.logger.LoggerCdb;

@Component
public class ComputerValidateur {

	private Map<String, ValidateurDTOException> errors;

	public Map<String, ValidateurDTOException> validate(ComputerDTOAdd computerDTOAdd) {
		this.errors = new HashMap<>();
		this.validateName(computerDTOAdd.getName());
		this.validateIntroduced(computerDTOAdd.getIntroduced());
		this.validateDiscontinued(computerDTOAdd.getDiscontinued());
		this.validateDateInterval(computerDTOAdd.getIntroduced(), computerDTOAdd.getDiscontinued());
		this.validateCompanyId(computerDTOAdd.getCompanyId());
		return errors;
	}

	public Map<String, ValidateurDTOException> validate(ComputerDTOUpdate computerDTOUpdate) {
		this.errors = new HashMap<>();

		this.validateComputerId(computerDTOUpdate.getId());
		this.validateName(computerDTOUpdate.getName());
		this.validateIntroduced(computerDTOUpdate.getIntroduced());
		this.validateDiscontinued(computerDTOUpdate.getDiscontinued());
		this.validateDateInterval(computerDTOUpdate.getIntroduced(), computerDTOUpdate.getDiscontinued());
		this.validateCompanyId(computerDTOUpdate.getCompanyId());
		return errors;

	}

	private void validateComputerId(String id) {
		try {
			validateId(id);
		} catch (IdNotValidException e) {
			this.errors.put("idInput", e);
		}

	}

	private void validateCompanyId(String id) {
		if (!("".equals(id))) {
			try {
				validateId(id);
			} catch (IdNotValidException e) {
				this.errors.put("companyIdInput", e);
			}

		}
	}

	private void validateId(String id) {
		try {
			int num = Integer.parseInt(id);

			if (num <= 0) {
				throw new IdNotValidException("Id not valid :" + id + " is negatif or =0");
			}

		} catch (RuntimeException e) {
			LoggerCdb.logWarn(ComputerValidateur.class.getName(), e);
			throw new IdNotValidException("Id not valid : " + id);

		}

	}

	private void validateName(String name) {
		if (!(name != null && !name.isBlank() && !name.equals("null"))) {
			this.errors.put("nameInput", new NameNotValidException("Name not valid : " + name));
		}
	}

	private void validateDateInterval(String introduced, String discontinued) {

		if (!("".equals(introduced) || "".equals(discontinued))) {
			if (!LocalDate.parse(introduced).isBefore(LocalDate.parse(discontinued))) {
				this.errors.put("dateInput", new DateIntervalNotValidException(
						"Date Interval not valid : " + introduced + ">" + discontinued));
			}
		}

	}

	private void validateIntroduced(String date) {
		try {
			validateDate(date);
		} catch (DateFormatNotValidException e) {
			this.errors.put("introducedInput", e);
		}
	}

	private void validateDiscontinued(String date) {
		try {
			validateDate(date);
		} catch (DateFormatNotValidException e) {
			this.errors.put("discontinuedInput", e);
		}
	}

	private void validateDate(String date) {
		try {
			if (!"".equals(date)) {
				LocalDate.parse(date);
			}

		} catch (RuntimeException e) {
			throw new DateFormatNotValidException("Date Format not valid : " + date);
		}

	}

}
