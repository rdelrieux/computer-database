package com.excilys.vue;

import java.util.List;

import com.excilys.model.Company;

public class CompanyCLI {

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
			System.out.println(company.getId() + " \t " + company.getName());
		}
		System.out.println("");
		
	}

	
	
	
}
