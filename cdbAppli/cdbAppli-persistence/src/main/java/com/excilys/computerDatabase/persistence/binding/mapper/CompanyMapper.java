package com.excilys.computerDatabase.persistence.binding.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.core.model.Company;
import com.excilys.computerDatabase.persistence.binding.dto.CompanyEntity;



@Component()
public class CompanyMapper   {
	
	
	public Company mapToCompany( CompanyEntity companyEntity ) {
		return new Company( companyEntity.getId() ,companyEntity.getName());	
	}

	public List<Company> maptoCompany( List<CompanyEntity> companyEntity) {
		
		return companyEntity.stream()
						.map(cOut -> this.mapToCompany(cOut) )
						.collect(Collectors.toList());
	}




	

}
