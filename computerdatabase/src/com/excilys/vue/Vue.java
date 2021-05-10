package com.excilys.vue;

import java.util.List;
import java.util.Scanner;

import com.excilys.connection.CdbConnection;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.DAO;
import com.excilys.persistence.dao.DAOFactory;
import com.excilys.persistence.dao.implement.CompanyDAO;

public class Vue {

	public Vue() {

	}

	public void start() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenue sur CDB \n");

		System.out.println("Connection ...  ");
		CdbConnection.getInstance();
		System.out.println("vous etes connecte \n");
		
		String res ;

		boolean end = false;
		while (!end ) {
			this.option();
			res = sc.nextLine();
			
			switch (res) {
			case "1" : this.listCompanies();
				break;
			case "2" :  this.listComputers();
				break;
			
			case "3":
				System.out.println("taper le num du computer (1-574) ");
				res = sc.nextLine();
				this.showComputerDetail(Integer.valueOf(res));
				break;
				
			case "exit":
				end = true;
				break;
			default : System.out.println("je n'ai pas compris votre demande");
				
				break;
			}
			
			
			
			

		}

		
		System.out.println("Bye \n");
		sc.close();
	}

	private void listComputers() {
		DAO<Computer> computerDao = DAOFactory.getComputer();
		List<Computer>  listCompany = computerDao.findAll();
		
		
		for (Computer computer : listCompany ) {
			System.out.println(computer.getId() + " \t "+computer.getName());
		}
		System.out.println( "");
	}

	private void listCompanies() {
		// TODO Auto-generated method stub
		CompanyDAO companyDao = (CompanyDAO) DAOFactory.getCompanyDAO();
		List<Company>  listCompany = companyDao.findAll();
		
		
		for (Company company : listCompany ) {
			System.out.println(company.getId() + " \t "+company.getName());
		}
		System.out.println( "");
	}

	private void showComputerDetail(int i) {
		// TODO Auto-generated method stub
		DAO<Computer> computerDao = DAOFactory.getComputer();
		Computer c = computerDao.find(i);
		if (c.getId() == 0) {
			System.out.println("il n'y a pas de computer numero " + i);

		} else {
			System.out.println(c.getId() + ", " + c.getName() + ", " + c.getIntroduced() + ", " + c.getDiscontinued()
					+ "," + c.getCompanyId());
		}
	}

	private void option() {
		System.out.println("pour voir la list des Companies taper 1");
		System.out.println("pour voir la list des Computers taper 2");
		System.out.println("pour voir les detail d'un Computer taper 3");
		System.out.println("pour exit taper exit");
	}
	
}
