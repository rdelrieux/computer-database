package com.excilys.model;

import java.time.LocalDate;

public class Computer {
	
	private int id = 0;
	
	private String name = "";
	
	private LocalDate introduced = LocalDate.of(0, 1, 1);
	
	private LocalDate discontinued = LocalDate.of(0, 1, 3);
	
	private int companyId = 0;
	
	
	public Computer () {
		
	}

	public Computer (int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public Computer(int id, String name, LocalDate introduced, LocalDate discontinued, int companyId) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		if (introduced.isBefore(discontinued) ) {
			this.discontinued = discontinued;
		}
		this.companyId = companyId;
	}




	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public LocalDate getIntroduced() {
		return introduced;
	}


	public LocalDate getDiscontinued() {
		return discontinued;
	}


	public int getCompanyId() {
		return companyId;
	}
	
	

}
