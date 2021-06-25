package com.excilys.computerDatabase.web.binding.dto;


public class ComputerDTO {

	private String id;
	
	private String name ;

	private String introduced ;

	private String discontinued ;

	private String companyName ;

	private ComputerDTO(ComputerDTOOutputBuilder computerDTOOutputBuilder) {
		this.id = computerDTOOutputBuilder.id;
		this.name = computerDTOOutputBuilder.name;
		this.introduced = computerDTOOutputBuilder.introduced;
		this.discontinued = computerDTOOutputBuilder.discontinued;
		this.companyName = computerDTOOutputBuilder.companyName;
	}
	public String getId() {
		return id;
	}
	
	
	public String getName() {
		return name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public String getCompanyName() {
		return companyName;
	}

	@Override
	public String toString() {
		return "ComputerDTOInput [name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", companyName=" + companyName + "]";
	}

	public static class ComputerDTOOutputBuilder {

		private String id;
		
		private String name ;

		private String introduced ;
		
		private String discontinued ;

		private String companyName ;


		public ComputerDTOOutputBuilder(String id ,String name) {
			this.id =id ;
			this.name = name;
			this.introduced = "";
			this.discontinued = "";
			this.companyName = "";
		}
	

		public ComputerDTOOutputBuilder withIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerDTOOutputBuilder withDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerDTOOutputBuilder withCompanyName(String companyName) {
			this.companyName = companyName;
			return this;
		}
		
		public ComputerDTO build() {
			return new ComputerDTO(this);
		}

	}
}

