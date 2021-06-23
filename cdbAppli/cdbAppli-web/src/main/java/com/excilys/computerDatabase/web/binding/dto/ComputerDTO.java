package com.excilys.computerDatabase.web.binding.dto;


public class ComputerDTO {

	private String id;
	
	private String name ;

	private String introduced="" ;

	private String discontinued="" ;

	private String companyName="" ;

	


	public String getId() {
		return id;
	}
	
	
	public String getName() {
		return name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public String getCompanyName() {
		return companyName;
	}
	

	
	
	public void setId(String id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}


	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
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

