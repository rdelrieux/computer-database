package com.excilys.model;

public class Company {
	
	private int id = 0;
	
	private String name = "";
	
	public Company() {
		
	}

	public Company(int id, String name) {
		this.id = id;
		this.name = name;
	}

	
	public Company(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	

}
