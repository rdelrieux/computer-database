package com.excilys.computerDatabase.model;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company + "]";
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
