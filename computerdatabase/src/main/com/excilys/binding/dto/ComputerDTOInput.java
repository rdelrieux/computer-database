package com.excilys.binding.dto;

public class ComputerDTOInput {
	
	private String name = "";
	
	private String introduced = "";
	
	private String discontinued = "";
		
	private String companyName = "";
	
	
	public ComputerDTOInput () {
		
	}


	public ComputerDTOInput(String name, String introduced, String discontinued, String companyName) {
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyName = companyName;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}


	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	@Override
	public String toString() {
		return "ComputerDTOInput [name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", companyName=" + companyName + "]";
	}


}

