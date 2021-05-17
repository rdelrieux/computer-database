package com.excilys.computerDatabase.binding.builder;

import com.excilys.computerDatabase.binding.dto.CompanyDTOSQL;

public class CompanyDTOSQLBuilder {

	private String id = "";
	
	private String name = "";
	
	public CompanyDTOSQLBuilder () {
		
	}

	public CompanyDTOSQLBuilder (String id, String name) {
		this.id = id;
		this.name = name;
	}

	public CompanyDTOSQL build() {
		return new CompanyDTOSQL(this.id,this.name);
	}
	
	
	public String getId() {
		return id;
	}

	public CompanyDTOSQLBuilder setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public CompanyDTOSQLBuilder setName(String name) {
		this.name = name;
		return this;
	}

		
	
}
