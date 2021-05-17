package com.excilys.computerDatabase.binding.builder;

import com.excilys.computerDatabase.binding.dto.ComputerDTOSQL;

public class ComputerDTOSQLBuilder {
	
	
private String id = "";
	
	private String name = "";
	
	private String introduced = "";
	
	private String discontinued = "";
	
	private String companyId = "";
	
	private String companyName = "";
	
	
	public ComputerDTOSQLBuilder () {
		
	}

	public ComputerDTOSQLBuilder (String id, String name) {
		this.id = id;
		this.name = name;
	}

	public ComputerDTOSQLBuilder(String id, String name, String introduced, String discontinued, String companyId,
			String companyName) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.companyName = companyName;
	}


	public ComputerDTOSQL build() {
		return new ComputerDTOSQL(this.id,this.name,this.introduced,this.discontinued,this.companyId,this.companyName);
	}
	
	
	public String getId() {
		return id;
	}

	public ComputerDTOSQLBuilder setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public ComputerDTOSQLBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public String getIntroduced() {
		return introduced;
	}

	public ComputerDTOSQLBuilder setIntroduced(String introduced) {
		this.introduced = introduced;
		return this;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public ComputerDTOSQLBuilder setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
		return this;
	}

	public String getCompanyId() {
		return companyId;
	}

	public ComputerDTOSQLBuilder setCompanyId(String companyId) {
		this.companyId = companyId;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public ComputerDTOSQLBuilder setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	

}
