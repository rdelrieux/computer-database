package com.excilys.computerDatabase.web.binding.validateur;


import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.computerDatabase.web.binding.dto.ComputerDTOAdd;
import com.excilys.computerDatabase.web.binding.dto.ComputerDTOUpdate;



@Component
public class ComputerValidateur implements Validator{

	private static final String COMPUTER_NAME_FORMAT = "^[a-zA-Z].*";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ComputerDTOAdd.class.equals(clazz) || ComputerDTOUpdate.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "computer name required , name is required");
		
		if (target instanceof ComputerDTOAdd) {
			ComputerDTOAdd computerDto = (ComputerDTOAdd) target;
			nameValidator(errors, computerDto.getName());
			dateValidator(errors, computerDto.getIntroduced(), computerDto.getDiscontinued());
			companyValidator(errors, computerDto.getCompanyId());
		} else {
			ComputerDTOUpdate computerDto = (ComputerDTOUpdate) target;
			nameValidator(errors, computerDto.getName());
			dateValidator(errors, computerDto.getIntroduced(), computerDto.getDiscontinued());
			companyValidator(errors, computerDto.getCompanyId());
		}
		
	}
	
	private void nameValidator(Errors errors, String name) {
		if (name == null || "null".equals(name) || !name.matches(COMPUTER_NAME_FORMAT)) {
			errors.rejectValue("name", "computer name invalid" , name +" is not a valid name ");
		}
		
	}
	
	private void companyValidator(Errors errors, String companyId) {
		if (! companyId.isEmpty() ) {
			int company = Integer.parseInt(companyId);
			if (company < 0) {
				errors.rejectValue("companyId", "computer company invalid" , "this company doesn't exist " );
			}
		}
		
	}

	private void dateValidator(Errors errors, String introduced, String discontinued) {
		if (!introduced.isEmpty() && introduced != null) {
			LocalDate dateIntroduced = LocalDate.parse(introduced);
			if (!discontinued.isEmpty() && discontinued != null) {
				LocalDate dateDiscontinued = LocalDate.parse(discontinued);
				if (dateIntroduced.isAfter(dateDiscontinued)) {
					errors.rejectValue("discontinued", "computer discontinued invalid" , "introduced date should be before discontinued date ");
				}
			}
		}
		
	}







	
	

}
