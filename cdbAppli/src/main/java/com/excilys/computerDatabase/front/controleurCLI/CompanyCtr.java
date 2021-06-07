package com.excilys.computerDatabase.front.controleurCLI;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.excilys.computerDatabase.back.dataBase.dao.CompanyDAO;
import com.excilys.computerDatabase.back.dataBase.exception.CompanyNotFoundException;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.service.CompanyService;
import com.excilys.computerDatabase.front.binding.mapper.CompanyMapper;
import com.excilys.computerDatabase.front.cli.CLI;
import com.excilys.computerDatabase.front.cli.ChoixUtilisateur;
import com.excilys.computerDatabase.front.cli.CompanyCLI;
import com.excilys.computerDatabase.logger.LoggerCdb;

@Controller
public class CompanyCtr {
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyCLI companyCLI;
	@Autowired
	private ChoixUtilisateur choixutilisateur;
	@Autowired
	@Qualifier("companyMapperCtr")
	private CompanyMapper companyMapper ;

	public void showListCompany() {
		
		companyCLI.showListCompany(
				 companyService.getListCompany().stream()
				.map(c -> companyMapper.mapToCompanyDTO(c))
				.collect(Collectors.toList())
				)
		;
	}

	public void showCompany() {
		try {
			Company company = companyService.getCompany(choixutilisateur.choixName(CLI.ENTER_COMPANY_NAME_MESSAGE));
			companyCLI.showCompany(companyMapper.mapToCompanyDTO(company));
		}
		catch(CompanyNotFoundException e) {
			LoggerCdb.logError(CompanyCtr.class.getName(), e);
		}
		
	
	}

	public void deletCompany() {
		Company company = companyService.getCompany(choixutilisateur.choixId(CLI.ENTER_ID_MESSAGE));
		companyService.deletCompany(company.getId());
		
	}
	
	

}
