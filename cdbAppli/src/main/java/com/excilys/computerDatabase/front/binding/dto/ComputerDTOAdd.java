package com.excilys.computerDatabase.front.binding.dto;

public class ComputerDTOAdd {

	private String name ;

	private String introduced ;

	private String discontinued ;

	private String companyId ;

	private ComputerDTOAdd(ComputerDTOAddBuilder computerDTOAddBuilder) {
		this.name = computerDTOAddBuilder.name;
		this.introduced = computerDTOAddBuilder.introduced;
		this.discontinued = computerDTOAddBuilder.discontinued;
		this.companyId = computerDTOAddBuilder.companyId;
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

	public String getCompanyId() {
		return companyId;
	}

	@Override
	public String toString() {
		return "ComputerDTOInput [name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyId + "]";
	}

	public static class ComputerDTOAddBuilder {

		
		
		private String name ;

		private String introduced ;
		
		private String discontinued ;

		private String companyId ;


		public ComputerDTOAddBuilder(String name) {
			this.name = name;
			this.introduced = "";
			this.discontinued = "";
			this.companyId ="";
		}
	

		public ComputerDTOAddBuilder withIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerDTOAddBuilder withDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerDTOAddBuilder withCompanyId(String companyId) {
			this.companyId = companyId;
			return this;
		}
		
		public ComputerDTOAdd build() {
			return new ComputerDTOAdd(this);
		}

	}
}
