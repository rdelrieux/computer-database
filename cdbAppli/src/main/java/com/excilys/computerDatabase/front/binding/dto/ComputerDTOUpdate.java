package com.excilys.computerDatabase.front.binding.dto;


public class ComputerDTOUpdate {

	private String id;
	
	private String name ;

	private String introduced ;

	private String discontinued ;

	private String companyId ;

	private ComputerDTOUpdate(ComputerDTOUpdateBuilder computerDTOUpdateBuilder) {
		this.id = computerDTOUpdateBuilder.id;
		this.name = computerDTOUpdateBuilder.name;
		this.introduced = computerDTOUpdateBuilder.introduced;
		this.discontinued = computerDTOUpdateBuilder.discontinued;
		this.companyId = computerDTOUpdateBuilder.companyId;
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

	public String getCompanyId() {
		return companyId;
	}

	@Override
	public String toString() {
		return "ComputerDTOInput [name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyId + "]";
	}

	public static class ComputerDTOUpdateBuilder {

		private String id ;
		
		private String name ;

		private String introduced ;
		
		private String discontinued ;

		private String companyId ;


		public ComputerDTOUpdateBuilder(String id,String name) {
			this.id = id;
			this.name = name;
			this.introduced = "";
			this.discontinued = "";
			this.companyId ="";
		}
	

		public ComputerDTOUpdateBuilder withIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerDTOUpdateBuilder withDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerDTOUpdateBuilder withCompanyId(String companyId) {
			this.companyId = companyId;
			return this;
		}
		
		public ComputerDTOUpdate build() {
			return new ComputerDTOUpdate(this);
		}

	}
}

