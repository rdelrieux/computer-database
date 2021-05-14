package com.excilys.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.vue.CLI;

public class ComputerMapping {

	private static final String COLONNE_ID = "id";
	private static final String COLONNE_NAME = "name";
	private static final String COLONNE_DATE_INTRIDUCED = "introduced";
	private static final String COLONNE_DATE_DISCONTINUED = "discontinued";
	private static final String COLONNE_COMPANY_ID = "company_id";
	private static final String COLONNE_COMPANY_NAME = "company.name";

	private static ComputerMapping instance;

	private ComputerMapping() {

	}

	public static ComputerMapping getInstance() {
		if (instance == null) {
			instance = new ComputerMapping();
		}
		return instance;
	}

	public List<Computer> toListComputer(ResultSet resultSet) {
		ArrayList<Computer> res = new ArrayList<Computer>();
		try {
			while (resultSet.next()) {
				Computer computer = new Computer(resultSet.getInt(COLONNE_ID), resultSet.getString(COLONNE_NAME));
				res.add(computer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public Computer toComputer(ResultSet result) throws SQLException {
		Computer computer = new Computer();

		if (result.first()) {
			computer = new Computer(result.getInt(COLONNE_ID), result.getString(COLONNE_NAME));
		}
		return computer;
	}

	public Computer toComputerFull(ResultSet result) throws SQLException {

		Computer computer = new Computer();

		if (result.first()) {

			int idRes = result.getInt(ComputerMapping.COLONNE_ID);
			String name = result.getString(ComputerMapping.COLONNE_NAME);
			LocalDate introduced = null;
			LocalDate discontinued = null;
			Company company = new Company();

			if (result.getDate(ComputerMapping.COLONNE_DATE_INTRIDUCED) != null) {
				introduced = result.getDate(ComputerMapping.COLONNE_DATE_INTRIDUCED).toLocalDate();

			}
			if (result.getDate(ComputerMapping.COLONNE_DATE_DISCONTINUED) != null) {
				discontinued = result.getDate(ComputerMapping.COLONNE_DATE_DISCONTINUED).toLocalDate();
			}
			if (result.getInt(ComputerMapping.COLONNE_COMPANY_ID) != 0) {
				company = new Company(result.getInt(COLONNE_COMPANY_ID), result.getString(COLONNE_COMPANY_NAME));
			}

			computer = new Computer(idRes, name, introduced, discontinued, company);
		}
		return computer;
	}

	public Computer toComputer(String[] computerInformation, Company company) {

		String name = computerInformation[0];
		String introduced = computerInformation[1];
		String discontinued = computerInformation[2];

		LocalDate introducedLocalDate = null;
		LocalDate distroducedLocalDate = null;

		if (!introduced.equals("null")) {
			int yearIntroduced = Integer.valueOf(introduced.split("-")[0]);
			int monthIntroduced = Integer.valueOf(introduced.split("-")[1]);
			int dayIntroduced = Integer.valueOf(introduced.split("-")[2]);
			introducedLocalDate = LocalDate.of(yearIntroduced, monthIntroduced, dayIntroduced);
		}

		if (!introduced.equals("null")) {
			int yearDiscontinued = Integer.valueOf(discontinued.split("-")[0]);
			int monthDiscontinued = Integer.valueOf(discontinued.split("-")[1]);
			int dayDiscontinued = Integer.valueOf(discontinued.split("-")[2]);
			distroducedLocalDate = LocalDate.of(yearDiscontinued, monthDiscontinued, dayDiscontinued);
		}

		Computer computer = new Computer(0, name, introducedLocalDate, distroducedLocalDate, company);

		return computer;
	}

	public Computer toComputer(Computer computer, String[] computerInformation, Company company) {
		String introduced = computerInformation[1];
		String discontinued = computerInformation[2];

		if (!computerInformation[0].equals(CLI.UNCHANGED)) {
			computer.setName(computerInformation[0]);
		}

		if (!introduced.equals(CLI.UNCHANGED)) {
			if (introduced.equals("null")) {
				computer.setIntroduced(null);
			} else {
				int yearIntroduced = Integer.valueOf(introduced.split("-")[0]);
				int monthIntroduced = Integer.valueOf(introduced.split("-")[1]);
				int dayIntroduced = Integer.valueOf(introduced.split("-")[2]);
				computer.setIntroduced(LocalDate.of(yearIntroduced, monthIntroduced, dayIntroduced));
			}
		}

		if (!discontinued.equals(CLI.UNCHANGED)) {
			if (discontinued.equals("null")) {
				computer.setDiscontinued(null);
			} else {
				int yearDiscontinued = Integer.valueOf(discontinued.split("-")[0]);
				int monthDiscontinued = Integer.valueOf(discontinued.split("-")[1]);
				int dayDiscontinued = Integer.valueOf(discontinued.split("-")[2]);
				computer.setIntroduced(LocalDate.of(yearDiscontinued, monthDiscontinued, dayDiscontinued));
			}
		}
		if (!computerInformation[3].equals(CLI.UNCHANGED)) {
			if (company.getId() == 0) {
				computer.setCompany(null);
			}else {
				computer.setCompany(company);
			}
		}

		return computer;
	}

}
