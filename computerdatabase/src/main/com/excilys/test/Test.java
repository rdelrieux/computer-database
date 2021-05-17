package com.excilys.test;


import com.excilys.binding.dto.ComputerDTOInput;
import com.excilys.binding.mapper.ComputerMapper;
import com.excilys.model.Page;
import com.excilys.vue.StartApplication;

public class Test {

	public static void main(String[] args) {

		
	
		/*
		ComputerDTOInput computerVide  = new ComputerDTOInput("computerVide","","","");
		ComputerMapper cm = ComputerMapper.getInstance();
		System.out.println( cm.toComputer(computerVide,"1","" ) );
	*/
		//Vue v = new Vue();
		//v.start();
	
		testStart();
		
		
		//CdbConnection.getInstance();

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
		application.connect();
		application.playMenu();
		application.stop();
	}
	
	
	


}
