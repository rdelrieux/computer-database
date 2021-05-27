package com.excilys.computerDatabase.test;

import com.excilys.computerDatabase.connection.CdbConnection;
import com.excilys.computerDatabase.persistence.dao.implement.CompanyDAO;
import com.excilys.computerDatabase.vue.StartApplication;

public class Test {

	public static void main(String[] args) {

		CdbConnection.getInstance().getConnection();
		
		CompanyDAO.getInstance().findAll();
		
	
		/*
		ComputerDTOInput computerVide  = new ComputerDTOInput("computerVide","","","");
		ComputerMapper cm = ComputerMapper.getInstance();
		System.out.println( cm.toComputer(computerVide,"1","" ) );
	*/
		//Vue v = new Vue();
		//v.start();
	
		//testStart();
		
		
		

		// testConnection();
		//testRequestCompany();

		//Computer computerTeset  = new Computer(1,"computerTest" );
		//System.out.println(computerTeset.getId() + ", " +computerTeset.getName() + ","+computerTeset.getIntroduced() +
		//		","+computerTeset.getDiscontinued() + ","+computerTeset.getCompany());
	
		//testRequestComputer();
	
	}
	private static void testStart() {
		StartApplication application = StartApplication.getInstance();
		application.start();
		application.playMenu();
		application.stop();
	}
	
	
	


}
