package com.excilys.computerDatabase.front.controleurCLI;


import java.util.stream.Collectors;

import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.service.CompanyService;
import com.excilys.computerDatabase.front.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.front.cli.CLI;
import com.excilys.computerDatabase.front.cli.ChoixUtilisateur;
import com.excilys.computerDatabase.front.cli.CompanyCLI;


public class CompanyCtr {
	
	private static CompanyCtr instance ;
	private CompanyService companyService;
	private CompanyCLI companyCLI;
	private ChoixUtilisateur choixutilisateur;
	private CompanyMapper companyMapper ;
	
	private CompanyCtr() {
		companyService = CompanyService.getInstance();
		companyCLI = CompanyCLI.getInstance();
		choixutilisateur = ChoixUtilisateur.getInstance();
		companyMapper = CompanyMapper.getInstance();
	}

	public static CompanyCtr getInstance()  {
		if (instance == null) {
			instance = new CompanyCtr();
		}
		return instance;
	}

	public void showListCompany() {
		
		companyCLI.showListCompany(
				 companyService.getListCompany().stream()
				.map(c -> companyMapper.mapToCompanyDTO(c))
				.collect(Collectors.toList())
				)
		;
	}

	public void showCompany() {
		Company company = companyService.getCompany(choixutilisateur.choixName(CLI.ENTER_COMPANY_NAME_MESSAGE));
		
		companyCLI.showCompany(companyMapper.mapToCompanyDTO(company));
	
	}

	public void deletCompany() {
		Company company = companyService.getCompany(choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE));
		
		companyService.deletCompany(company.getId());
		
	}
	
	

}
