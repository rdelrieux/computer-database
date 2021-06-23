package com.excilys.computerDatabase.web.binding.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompanyDTO {
	
	private String id ;
	
	private String name;
	

	public CompanyDTO(String id, String name) {
		this.id = id;
		this.name = name;
		
	}

	public CompanyDTO() {
		this.id = "";
		this.name = "";
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "CompanyDTO [id=" + id + ", name=" + name + "]";
	}
	
	
}
