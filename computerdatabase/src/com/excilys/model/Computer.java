package com.excilys.model;

import java.time.LocalDate;

public class Computer {
	
	private int id = 0;
	
	private String name = "";
	
	private LocalDate introduced = null;
	
	private LocalDate discontinued = null;
	
	private Company company = new Company();
	
	
	public Computer () {
		
	}

	public Computer (int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public Computer(int id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
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
	
		this.company = company;
	}




	public void setName(String name) {
		this.name = name;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public void setCompany(Company company) {
		this.company = company;
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


	public Company getCompany() {
		return company;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company + "]";
	}
	
	
	

}
