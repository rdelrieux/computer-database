package com.excilys.binding.dto;


public class ComputerDTOSQL {
	
	
	private String id = "";
	
	private String name = "";
	
	private String introduced = "";
	
	private String discontinued = "";
	
	private String companyId = "";
	
	private String companyName = "";
	
	
	public ComputerDTOSQL () {
		
	}

	public ComputerDTOSQL (String id, String name) {
		this.id = id;
		this.name = name;
	}

	public ComputerDTOSQL(String id, String name, String introduced, String discontinued, String companyId,
			String companyName) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", companyId=" + companyId + ", companyName=" + companyName + "]";
	}
	


	
	
	

}
