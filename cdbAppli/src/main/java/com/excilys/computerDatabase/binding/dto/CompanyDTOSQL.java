package com.excilys.computerDatabase.binding.dto;

public class CompanyDTOSQL {
	
	private String id ;
	
	private String name ;
	
	public CompanyDTOSQL (String id, String name) {
		this.id = id;
		this.name = name;
	}


	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "CompanyDTOSQL [id=" + id + ", name=" + name +"]";
	}
	
	

}
