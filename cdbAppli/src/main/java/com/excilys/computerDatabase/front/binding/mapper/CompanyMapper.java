package com.excilys.computerDatabase.front.binding.mapper;

import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.front.binding.dto.CompanyDTO;





public class CompanyMapper {
	
	
	
	private static CompanyMapper instance;

	private CompanyMapper() {
	}

	public static CompanyMapper getInstance() {
		if (instance == null) {
			instance = new CompanyMapper();
		}
		return instance;
	}
	

	public CompanyDTO  mapToCompanyDTO(Company company) {
		if ( company == null ) {
			return new CompanyDTO();
		}
		return	new CompanyDTO(""+company.getId() , company.getName() ) ;	
	}

}
