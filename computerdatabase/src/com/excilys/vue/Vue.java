package com.excilys.vue;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.excilys.connection.CdbConnection;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.DAO;
import com.excilys.persistence.dao.DAOFactory;
import com.excilys.persistence.dao.implement.CompanyDAO;

public class Vue {

	private Scanner sc = new Scanner(System.in);
	
	public Vue() {

	}

	public void start() {
		
		System.out.println("Bienvenue sur CDB \n");

		System.out.println("Connection ...  ");
		try {
			CdbConnection.getInstance("admincdb" , "azerty1234");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("vous etes connecte ");

		String res;

		boolean end = false;
		while (!end) {
			this.option();
			res = sc.nextLine();

			switch (res) {
			case "1":
				this.listCompanies();
				break;
			case "2":
				this.listComputers();
				break;

			case "3":
				System.out.println("taper le num du computer (1-574) ");
				res = sc.nextLine();
				this.showComputerDetail(Integer.valueOf(res));

				break;
			case "4":
				this.createComputer();
				break;
			case "5":
				System.out.println("not implemented yet");
				break;
			case "6" :
				System.out.println("not implemented yet");
			break;
		
			case "exit":
				end = true;
				break;
			default:
				System.out.println("je n'ai pas compris votre demande");

				break;
			}

		}

		System.out.println("Bye \n");
		sc.close();
	}

	private void createComputer() {
		// TODO Auto-generated method stub
		System.out.println("Tapez le nom du Computer ");
		String nameComputer = sc.nextLine();
		
		System.out.println("Tapez la date intoduced (yyyy/mm/dd) sinon tapez n");
		String introduced = sc.nextLine();
		
		System.out.println("la date discontinued (yyyy/mm/dd)  sinon tapez n");
		String discontinued = sc.nextLine();
		
		
		System.out.println("Tapez le nom de la Company sinon tapez n ");
		String nameCompany = sc.nextLine();
		
		System.out.println("Etes vous sur de vouloir ajouter le Computer :  o/n");
		System.out.println(nameComputer +", "+ introduced +", "+ discontinued +", "+ nameCompany);
		String creation = sc.nextLine();
		
		
		Company company =((CompanyDAO) DAOFactory.getCompanyDAO()).find(nameCompany);
		
		
		
	}

	private void listComputers() {
		DAO<Computer> computerDao = DAOFactory.getComputer();
		List<Computer> listCompany = computerDao.findAll();

		for (Computer computer : listCompany) {
			System.out.println(computer.getId() + " \t " + computer.getName());
		}

	}

	private void listCompanies() {
		// TODO Auto-generated method stub
		DAO<Company> companyDao = DAOFactory.getCompanyDAO();
		List<Company> listCompany = companyDao.findAll();

		for (Company company : listCompany) {
			System.out.println(company.getId() + " \t " + company.getName());
		}
		System.out.println("");
	}

	private void showComputerDetail(int i) {
		// TODO Auto-generated method stub
		DAO<Computer> computerDao = DAOFactory.getComputer();
		Computer computer = computerDao.find(i);
		if (computer.getId() == 0) {
			System.out.println("il n'y a pas de computer numero " + i);

		} else {

			String intoduced = "pas definit";
			String discontinued = "pas definit";
			String companyName = "pas definit";
			if (computer.getIntroduced() != null) {
				intoduced = computer.getIntroduced().toString();
			}
			if (computer.getDiscontinued() != null) {
				discontinued = computer.getDiscontinued().toString();
			}
			if (computer.getCompanyId() != 0) {

				DAO<Company> companyDao = DAOFactory.getCompanyDAO();
				Company company = companyDao.find(computer.getCompanyId());
				companyName = company.getName();
			}

			System.out.println(computer.getId() + ", " + computer.getName() + ", " + intoduced + ", " + discontinued
					+ "," + companyName);
		}
	}

	private void option() {
		System.out.println();
		System.out.println("pour voir la liste des Companies tapez 1");
		System.out.println("pour voir la liste des Computers tapez 2");
		System.out.println("pour voir les details d'un Computer tapez 3");
		System.out.println("pour ajouter un Computer tapez 4");
		System.out.println("pour mettre a jour un Computer tapez 5");
		System.out.println("pour supprimer un Computer tapez 6");
		System.out.println("pour partir tapez exit");
	}

}
