package com.excilys.controleur;


import com.excilys.service.CompanyService;
import com.excilys.vue.CompanyCLI;

public class CompanyCtr {
	
	private static CompanyCtr instance ;
	private CompanyService companyService;
	private CompanyCLI companyCLI;
	
	private CompanyCtr() {
		companyService = CompanyService.getInstance();
		companyCLI = CompanyCLI.getInstance();
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
	
	

}
