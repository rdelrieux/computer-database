package com.excilys.computerDatabase.binding.dto;


public class ComputerDTOSQL {

	private String id;

	private String name;

	private String introduced;

	private String discontinued;

	private String companyId;

	private String companyName;

	public ComputerDTOSQL(ComputerDTOSQLBuilder computerDTOSQLBuilder) {
		super();
		this.id = computerDTOSQLBuilder.id;
		this.name = computerDTOSQLBuilder.name;
		this.introduced = computerDTOSQLBuilder.introduced;
		this.discontinued = computerDTOSQLBuilder.discontinued;
		this.companyId = computerDTOSQLBuilder.companyId;
		this.companyName = computerDTOSQLBuilder.companyName;
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

	public String getCompanyName() {
		return companyName;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", companyId=" + companyId + ", companyName=" + companyName + "]";
	}

	public static class ComputerDTOSQLBuilder {

		private String id;

		private String name;

		private String introduced;

		private String discontinued;

		private String companyId;

		private String companyName;


		public ComputerDTOSQLBuilder(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public ComputerDTOSQL build() {
			return new ComputerDTOSQL(this);
		}

	

		public ComputerDTOSQLBuilder withIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

	
		public ComputerDTOSQLBuilder withDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}


		public ComputerDTOSQLBuilder withCompanyId(String companyId) {
			this.companyId = companyId;
			return this;
		}

		public ComputerDTOSQLBuilder withCompanyName(String companyName) {
			this.companyName = companyName;
			return this;
		}

	}

}
