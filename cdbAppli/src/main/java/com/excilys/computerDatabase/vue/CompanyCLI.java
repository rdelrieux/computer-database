package com.excilys.computerDatabase.vue;

import java.util.List;

import com.excilys.computerDatabase.model.Company;

public class CompanyCLI extends CLI{

	private static CompanyCLI instance ;

	
	private CompanyCLI() {
		
	}

	public static CompanyCLI getInstance()  {
		if (instance == null) {
			instance = new CompanyCLI();
		}
		return instance;
	}

	public void showListCompany(List<Company> listCompany) {
		for (Company company : listCompany) {
			System.out.println(company.getName());
		}
		System.out.println("");
		
	}

	public void showCompany(Company company) {
		if (company == null) {
			System.out.println(CLI.COMPANY_NOT_FOUND_MESSAGE);
		}else {
			System.out.println("ID = "+company.getId() +", "+company.getName());
		}
		
		
	}

	

	
	
	
}
