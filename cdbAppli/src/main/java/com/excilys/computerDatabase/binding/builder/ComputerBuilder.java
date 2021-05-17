package com.excilys.computerDatabase.binding.builder;

import java.time.LocalDate;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;

public class ComputerBuilder {
	
	private int id = 0;
	
	private String name = "";
	
	private LocalDate introduced = null;
	
	private LocalDate discontinued = null;
	
	private Company company = null;
	
	
	public ComputerBuilder () {
		
	}

	public Computer build() {
		return new Computer (this.id,this.name,this.introduced,this.discontinued,this.company);
	}


	public ComputerBuilder setId(int id) {
		this.id = id;
		return this;
	}


	public ComputerBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public ComputerBuilder setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
		return this;
	}

	public ComputerBuilder setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
		return this;
	}

	public ComputerBuilder setCompany(Company company) {
		this.company = company;
		return this;
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
