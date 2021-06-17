package com.excilys.computerDatabase.back.dataBase.binding.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.back.dataBase.binding.dto.CompanyEntity;
import com.excilys.computerDatabase.back.model.Company;

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

	public CompanyEntity mapToCompanyEntity(Company company) {
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setId(company.getId());
		companyEntity.setName(company.getName());
		return companyEntity;	
	}


	

}
