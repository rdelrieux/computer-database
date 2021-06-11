package com.excilys.computerDatabase.front.binding.dto;


public class ComputerDTOUpdate {

	private String id;
	
	private String name ;

	private String introduced ;

	private String discontinued ;

	private String companyId ;

	
	
	public ComputerDTOUpdate() {
		this.introduced = "";
		this.discontinued = "";
		this.companyId = "";
	}
	
	
	public ComputerDTOUpdate(String id) {
		super();
		this.id = id;
		this.name = "";
		this.introduced = "";
		this.discontinued = "";
		this.companyId = "";
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



	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}



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

	public String getCompanyId() {
		return companyId;
	}


	@Override
	public String toString() {
		return "ComputerDTOUpdate [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", companyId=" + companyId + "]";
	}



	
}

