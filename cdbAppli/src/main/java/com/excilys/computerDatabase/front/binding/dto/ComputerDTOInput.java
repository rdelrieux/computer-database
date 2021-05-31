package com.excilys.computerDatabase.front.binding.dto;

public class ComputerDTOInput {

	private String name ;

	private String introduced ;

	private String discontinued ;

	private CompanyDTO companyDTO ;

	private ComputerDTOInput(ComputerDTOInputBuilder computerDTOInputBuilder) {
		this.name = computerDTOInputBuilder.name;
		this.introduced = computerDTOInputBuilder.introduced;
		this.discontinued = computerDTOInputBuilder.discontinued;
		this.companyDTO = computerDTOInputBuilder.companyDTO;
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

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}

	@Override
	public String toString() {
		return "ComputerDTOInput [name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyDTO + "]";
	}

	public static class ComputerDTOInputBuilder {

		
		
		private String name ;

		private String introduced ;
		
		private String discontinued ;

		private CompanyDTO companyDTO ;


		public ComputerDTOInputBuilder(String name) {
			this.name = name;
			this.introduced = "";
			this.discontinued = "";
			this.companyDTO = new CompanyDTO("","");
		}
	

		public ComputerDTOInputBuilder withIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerDTOInputBuilder withDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerDTOInputBuilder withCompanyDTO(CompanyDTO companyDTO) {
			this.companyDTO = companyDTO;
			return this;
		}
		
		public ComputerDTOInput build() {
			return new ComputerDTOInput(this);
		}

	}
}
