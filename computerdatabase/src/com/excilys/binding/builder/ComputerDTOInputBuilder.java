package com.excilys.binding.builder;

import com.excilys.binding.dto.ComputerDTOInput;

public class ComputerDTOInputBuilder {

	private String id = "";

	private String name = "";

	private String introduced = "";

	private String discontinued = "";

	private String companyName = "";

	public ComputerDTOInputBuilder() {

	}

	public ComputerDTOInputBuilder(String name, String introduced, String discontinued, String companyName) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyName = companyName;
	}

	public ComputerDTOInputBuilder(String id, String name, String introduced, String discontinued, String companyName) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyName = companyName;
	}

	public ComputerDTOInput build() {
		return new ComputerDTOInput(this.id , this.name, this.introduced, this.discontinued, this.companyName);
	}
	
	public String getId() {
		return id;
	}

	public ComputerDTOInputBuilder setId(String id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}

	public ComputerDTOInputBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public String getIntroduced() {
		return introduced;
	}

	public ComputerDTOInputBuilder setIntroduced(String introduced) {
		this.introduced = introduced;
		return this;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public ComputerDTOInputBuilder setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public ComputerDTOInputBuilder setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

}
