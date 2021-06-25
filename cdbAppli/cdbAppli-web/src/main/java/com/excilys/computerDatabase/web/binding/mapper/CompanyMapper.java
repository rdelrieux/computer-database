package com.excilys.computerDatabase.web.binding.mapper;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.core.model.Company;
import com.excilys.computerDatabase.web.binding.dto.CompanyDTO;




@Component("companyMapperCtr")
public class CompanyMapper {
	

	public CompanyDTO  mapToCompanyDTO(Company company) {
		if ( company == null ) {
			return new CompanyDTO();
		}
		return	new CompanyDTO(""+company.getId() , company.getName() ) ;	
	}

}
