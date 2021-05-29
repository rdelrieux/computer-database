package com.excilys.computerDatabase.front.controleurCLI;

import com.excilys.computerDatabase.back.service.CompanyService;
import com.excilys.computerDatabase.front.cli.CLI;
import com.excilys.computerDatabase.front.cli.ChoixUtilisateur;
import com.excilys.computerDatabase.front.cli.CompanyCLI;


public class CompanyCtr {
	
	private static CompanyCtr instance ;
	private CompanyService companyService;
	private CompanyCLI companyCLI;
	private ChoixUtilisateur choixutilisateur;
	
	private CompanyCtr() {
		companyService = CompanyService.getInstance();
		companyCLI = CompanyCLI.getInstance();
		choixutilisateur = ChoixUtilisateur.getInstance();
	}

	public static CompanyCtr getInstance()  {
		if (instance == null) {
			instance = new CompanyCtr();
		}
		return instance;
	}

	public void showListCompany() {
		companyCLI.showListCompany(companyService.getListCompany());
	}

	public void showCompany() {
		Company company = companyService.getCompany(choixutilisateur.choixName(CLI.ENTER_COMPANY_NAME_MESSAGE));
		companyCLI.showCompany(company);
	
	}
	
	

}
