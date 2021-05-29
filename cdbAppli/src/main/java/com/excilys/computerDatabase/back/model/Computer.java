package com.excilys.computerDatabase.back.model;

import java.time.LocalDate;

public class Computer {

	private int id;

	private String name;

	private LocalDate introduced;

	private LocalDate discontinued;

	private Company company;

	private Computer(ComputerBuilder computerBuilder) {
		this.id = computerBuilder.id;
		this.name = computerBuilder.name;
		this.introduced = computerBuilder.introduced;
		this.discontinued = computerBuilder.discontinued;
		this.discontinued = computerBuilder.discontinued;
		this.company = computerBuilder.company;
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


	public static class ComputerBuilder {
		
		private int id ;
		
		private String name ;
		
		private LocalDate introduced ;
		
		private LocalDate discontinued ;
		
		private Company company ;
		
		
		public ComputerBuilder (int id ,String name ) {
			this.id = id;
			this.name = name;
		}

		public Computer build() {
			return new Computer (this);
		}

		public ComputerBuilder withIntroduced(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerBuilder withDiscontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerBuilder withCompany(Company company) {
			this.company = company;
			return this;
		}

	
	}

}
