package com.excilys.computerDatabase.front.binding.mapper;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.front.binding.dto.CompanyDTO;

@Component("companyMapperCtr")
public class CompanyMapper {
	

	public CompanyDTO  mapToCompanyDTO(Company company) {
		if ( company == null ) {
			return new CompanyDTO();
		}
		return	new CompanyDTO(""+company.getId() , company.getName() ) ;	
	}

}
