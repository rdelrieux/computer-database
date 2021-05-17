package com.excilys.binding.builder;

import com.excilys.model.Company;


public class CompanyBuilder {
	
	private int id = 0;
	
	private String name = "";
	
	
	public CompanyBuilder () {
		
	}

	public Company build() {
		return new Company (this.id,this.name);
	}


	public CompanyBuilder setId(int id) {
		this.id = id;
		return this;
	}


	public CompanyBuilder setName(String name) {
		this.name = name;
		return this;
	}


	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name+ "]";
	}

}
