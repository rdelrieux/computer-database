package com.excilys.computerDatabase.back.dataBase.binding.dto;


public class ComputerDTOAdd {
	
	private String name;

	private String introduced;

	private String discontinued;

	
	private String companyId ;

	private ComputerDTOAdd(ComputerDTOAddBuilder computerDTOAddBuilder) {
		super();
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

	public String getcompanyId() {
		return companyId;
	}



	public static class ComputerDTOAddBuilder {

		
		private String name;

		private String introduced ;

		private String discontinued;
		
		private String companyId;



		public ComputerDTOAddBuilder( String name) {
			this.name = name;
		}

		public ComputerDTOAdd build() {
			return new ComputerDTOAdd(this);
		}

	

		public ComputerDTOAddBuilder withIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

	
		public ComputerDTOAddBuilder withDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerDTOAddBuilder withCompanyId( String companyId ) {
			this.companyId = companyId;
			return this;
		}

	}

}
