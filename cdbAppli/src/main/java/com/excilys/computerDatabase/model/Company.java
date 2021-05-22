package com.excilys.computerDatabase.model;

public class Company implements Comparable<Company> {
	
	private int id;	
	private String name ;


	public Company(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Company company) {
		 return this.getName().toUpperCase().compareTo(company.getName().toUpperCase());
	}

}

