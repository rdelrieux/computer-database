package test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import connection.CdbConnection;
import model.Company;
import model.Computer;
import persistence.dao.DAO;
import persistence.dao.DAOFactory;
import vue.Vue;

public class Test {

	public static void main(String[] args) throws SQLException {

		Vue v = new Vue();
		v.start();
		
		
		// CdbConnection.getInstance();

		// testConnection();
		//testRequestCompany();

		//Computer c  = new Computer(1,"c",LocalDate.of(2018, 1, 1),LocalDate.of(2019, 1, 1),1 );
		//System.out.println(c.getId() + ", " +c.getName() + ","+c.getIntroduced() + ","+c.getDiscontinued() + ","+c.getCompanyId());
	
		//testRequestComputer();
	
	}

	private static void testRequestComputer() {
		// TODO Auto-generated method stub
		DAO<Computer> computerDao = DAOFactory.getComputer();

		for (int i = 1; i < 44; i++) {
			Computer c = computerDao.find(i);
			if (c.getId() == 0) {
				System.out.println("il n'y a pas de computer numero " + i);

			} else {
				System.out.println(c.getId() + ", " +c.getName() + ","+c.getIntroduced() + ","+c.getDiscontinued() + ","+c.getCompanyId());

			}
		}
	}
	
	
	private static void testRequestCompany() {
		// TODO Auto-generated method stub
		DAO<Company> companyDao = DAOFactory.getCompanyDAO();

		for (int i = 1; i < 44; i++) {
			Company company = companyDao.find(i);
			if (company.getId() == 0) {
				System.out.println("il n'y a pas de company numero " + i);

			} else {
				System.out.println(company.getId() + " - NOM : " + company.getName());

			}
		}
	}

	private static void testConnection() throws SQLException {
		// TODO Auto-generated method stub
		// Création d'un objet Statement
		Statement state = CdbConnection.getInstance().createStatement();
		// L'objet ResultSet contient le résultat de la requête SQL
		ResultSet result = state.executeQuery("SELECT * FROM company");
		// On récupère les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();

		while (result.next()) {
			for (int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + result.getObject(i).toString() + "\t ");

			System.out.println("\n--------------------------------------------------------------------");

		}

		result.close();
		state.close();
	}
	


}
