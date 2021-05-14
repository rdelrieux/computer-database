package com.excilys.controleur;


import com.excilys.model.Company;
import com.excilys.service.CompanyService;
import com.excilys.vue.CLI;
import com.excilys.vue.ChoixUtilisateur;
import com.excilys.vue.CompanyCLI;

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
