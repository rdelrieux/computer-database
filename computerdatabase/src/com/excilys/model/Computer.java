package com.excilys.model;

import java.time.LocalDate;

public class Computer {
	
	private int id = 0;
	
	private String name = "";
	
	private LocalDate introduced = null;
	
	private LocalDate discontinued = null;
	
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
		
		if (introduced == null || discontinued == null) {	
			this.discontinued = discontinued;
		}
		else {
			if (introduced.isBefore(discontinued) ) {
				this.discontinued = discontinued;
			}
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
