package com.excilys.computerDatabase.back.dataBase.binding.dto;


public class CompanyDTOOutput {
	
	private String id ;
	
	private String name;
	

	public CompanyDTOOutput(String id, String name) {
		this.id = id;
		this.name = name;
		
	}

	public CompanyDTOOutput() {
		this.id = "";
		this.name = "";
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	
	
	
	

}
