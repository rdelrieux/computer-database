package com.excilys.computerDatabase.front.cli;

import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.front.binding.dto.CompanyDTO;

@Component
public class CompanyCLI extends CLI{

	public void showListCompany(List<CompanyDTO> listCompany) {
		for (CompanyDTO company : listCompany) {
			System.out.println(company.getName());
		}
		System.out.println("");
		
	}

	public void showCompany(CompanyDTO companyDTO) {
		if (companyDTO == null) {
			System.out.println(CLI.COMPANY_NOT_FOUND_MESSAGE);
		}else {
			System.out.println("ID = "+companyDTO.getId() +", "+companyDTO.getName());
		}
		
		
	}

	

	
	
	
}
