package com.excilys.computerDatabase.back.dataBase.binding.dto;


public class ComputerDTOOutput {

	private String id ;
	
	private String name;

	private String introduced;

	private String discontinued;

	
	private CompanyDTOOutput companyDTOOutput;

	private ComputerDTOOutput(ComputerDTOOutputBuilder computerDTOOutputBuilder) {
		super();
		this.id = computerDTOOutputBuilder.id;
		this.name = computerDTOOutputBuilder.name;
		this.introduced = computerDTOOutputBuilder.introduced;
		this.discontinued = computerDTOOutputBuilder.discontinued;
		this.companyDTOOutput = computerDTOOutputBuilder.companyDTOOutput;
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

	public CompanyDTOOutput getCompanyDTOOutput() {
		return companyDTOOutput;
	}



	public static class ComputerDTOOutputBuilder {

		private String id; 
		
		private String name;

		private String introduced = "";

		private String discontinued = "";

		private CompanyDTOOutput companyDTOOutput = new CompanyDTOOutput() ;


		public ComputerDTOOutputBuilder( String id , String name) {
			this.id = id;
			this.name = name;
		}

		public ComputerDTOOutput build() {
			return new ComputerDTOOutput(this);
		}

	

		public ComputerDTOOutputBuilder withIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

	
		public ComputerDTOOutputBuilder withDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerDTOOutputBuilder withCompany( CompanyDTOOutput companyDTOOutput ) {
			this.companyDTOOutput = companyDTOOutput;
			return this;
		}

	}

}
