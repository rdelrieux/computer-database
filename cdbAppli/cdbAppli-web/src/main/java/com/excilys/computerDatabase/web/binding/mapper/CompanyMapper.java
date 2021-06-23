package com.excilys.computerDatabase.web.binding.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.core.model.Company;
import com.excilys.computerDatabase.web.binding.dto.CompanyDTO;




@Component("companyMapperWeb")
public class CompanyMapper {
	
	public CompanyDTO  mapToCompanyDTO(Company company) {
		if ( company == null ) {
			return new CompanyDTO();
		}
		return	new CompanyDTO(""+company.getId() , company.getName() ) ;	
	}
	
	public List<CompanyDTO>  mapToCompanyDTO(List<Company> company) {
		return	company.stream().map(c -> this.mapToCompanyDTO(c)).collect(Collectors.toList());	
	}

}
