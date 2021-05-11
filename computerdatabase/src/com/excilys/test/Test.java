package com.excilys.test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.excilys.connection.CdbConnection;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.DAO;
import com.excilys.persistence.dao.DAOFactory;
import com.excilys.vue.StartApplication;
import com.excilys.vue.Vue;

public class Test {

	public static void main(String[] args) throws SQLException {

		//Vue v = new Vue();
		//v.start();
	
		testStart();
		
		
		// CdbConnection.getInstance();

		// testConnection();
		//testRequestCompany();

		//Computer c  = new Computer(1,"c",LocalDate.of(2018, 1, 1),LocalDate.of(2019, 1, 1),1 );
		//System.out.println(c.getId() + ", " +c.getName() + ","+c.getIntroduced() + ","+c.getDiscontinued() + ","+c.getCompanyId());
	
		//testRequestComputer();
	
	}
	private static void testStart() {
		StartApplication application = StartApplication.getInstance();
		application.start();
		application.connect();
		application.playMenu();
		application.stop();
	}
	
	
	


}
