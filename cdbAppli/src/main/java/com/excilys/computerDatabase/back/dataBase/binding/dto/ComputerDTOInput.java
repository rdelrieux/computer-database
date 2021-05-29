package com.excilys.computerDatabase.back.dataBase.binding.dto;

public class ComputerDTOInput {

		private String name;

		private String introduced;

		private String discontinued;

		private String companyId;


		public ComputerDTOInput(ComputerDTOInputBuilder computerDTOInputBuilder) {
			super();
			this.name = computerDTOInputBuilder.name;
			this.introduced = computerDTOInputBuilder.introduced;
			this.discontinued = computerDTOInputBuilder.discontinued;
			this.companyId = computerDTOInputBuilder.companyId;
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

		public static class ComputerDTOInputBuilder {

			private String name;

			private String introduced;

			private String discontinued;

			private String companyId;




			public ComputerDTOInputBuilder( String name) {
			
				this.name = name;
			}

			public ComputerDTOInput build() {
				return new ComputerDTOInput(this);
			}

		

			public ComputerDTOInputBuilder withIntroduced(String introduced) {
				this.introduced = introduced;
				return this;
			}

		
			public ComputerDTOInputBuilder withDiscontinued(String discontinued) {
				this.discontinued = discontinued;
				return this;
			}


			public ComputerDTOInputBuilder withCompanyId(String companyId) {
				this.companyId = companyId;
				return this;
			}

		}

}
