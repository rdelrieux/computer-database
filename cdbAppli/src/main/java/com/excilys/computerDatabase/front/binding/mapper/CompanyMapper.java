package com.excilys.computerDatabase.front.binding.mapper;

import java.time.LocalDate;

import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.front.binding.dto.CompanyDTO;
import com.excilys.computerDatabase.front.binding.validateur.CompanyValidateur;




public class CompanyMapper {
	
	private CompanyValidateur companyValidateur ; 
	
	
	private static CompanyMapper instance;

	private CompanyMapper() {
		companyValidateur = CompanyValidateur.getInstance();
	}

	public static CompanyMapper getInstance() {
		if (instance == null) {
			instance = new CompanyMapper();
		}
		return instance;
	}
	
public Company mapToCompany(CompanyDTO companyDTO) {
		
		this.companyValidateur.validate(companyDTO);
		
		if ("".equals(companyDTO.getId()) ) {
			return null;
		}
	
		return	new Company(Integer.parseInt(companyDTO.getId()) , companyDTO.getName() ) ;	
	}
	

	public CompanyDTO  mapToCompanyDTO(Company company) {
		if ( company == null ) {
			return new CompanyDTO("","");
		}
		return	new CompanyDTO(""+company.getId() , company.getName() ) ;	
	}

}
