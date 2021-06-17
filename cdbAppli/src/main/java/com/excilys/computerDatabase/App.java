package com.excilys.computerDatabase;


import com.excilys.computerDatabase.back.dataBase.dao.CompanyDAO;
import com.excilys.computerDatabase.back.dataBase.dao.ComputerDAO;
import com.excilys.computerDatabase.configuration.RootConfig;
import com.excilys.computerDatabase.front.cli.StartApplication;
import com.excilys.computerDatabase.front.session.Session;
import com.excilys.computerDatabase.logger.LoggerCdb;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;





public class App {
	
static int[][] game ;

	public static void main(String[] args) {
		
		//utiliser de l'AOP pour calculer et logger les temps d'exécution des méthodes de vos DAO
		
		LoggerCdb.logDebug("", "start creationRoot methode");

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);	 
	
		
		
		CompanyDAO daoCompany = context.getBean(CompanyDAO.class);
		ComputerDAO daoComputer = context.getBean(ComputerDAO.class);
		
		Session s = new Session ();
		String search = "Mac";
		s.getPage().setNombreElementRequet(daoComputer.searchNombreElementRequet(search));
		s.setSearch(search);
		;
		System.out.println( );

		 daoComputer.search(s).stream().forEach(c-> System.out.println(c));
		context.close();
	}



	

	

	private static void app(ConfigurableApplicationContext context) {
		StartApplication application = (StartApplication) context.getBean("startApplication");
		application.start();
		application.playMenu();
		application.stop();
		
		
	}
}
