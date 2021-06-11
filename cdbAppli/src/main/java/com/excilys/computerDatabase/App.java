package com.excilys.computerDatabase;


import com.excilys.computerDatabase.back.dataBase.dao.CompanyDAO;
import com.excilys.computerDatabase.back.dataBase.dao.ComputerDAO;
import com.excilys.computerDatabase.back.model.Company;
import com.excilys.computerDatabase.back.model.Computer;


import com.excilys.computerDatabase.configuration.RootConfig;
import com.excilys.computerDatabase.front.cli.StartApplication;
import com.excilys.computerDatabase.front.session.Session;
import com.excilys.computerDatabase.logger.LoggerCdb;

import java.time.LocalDate;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;





public class App {
	
static int[][] game ;

	public static void main(String[] args) {
		LoggerCdb.logDebug("", "start creationRoot methode");

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);	 
		
	
		ComputerDAO dao = context.getBean(ComputerDAO.class);
		CompanyDAO daoy = context.getBean(CompanyDAO.class);
		Computer c1 = new Computer.ComputerBuilder(0 , "test2101")
				.withIntroduced(LocalDate.of(2010,10,10))
				.withDiscontinued(LocalDate.of(2010,10,10))
				.withCompany(new Company(46 , "Salutez"))
				.build();
		
		
		Computer c2 = new Computer.ComputerBuilder(0 , "test2102")
				.build();
		
		//dao.addComputer(c1);
		//dao.addComputer(c2);


		//System.out.println("locking test");
		Session session = new Session ();
		session.setSearch("test210");
		//dao.search(session).stream().forEach(c-> System.out.println(c));
		System.out.println(daoy.find("Salut").getId());
		System.out.println(daoy.find("Salut").getName());
		daoy.delet(44);

		
		
		context.close();
	}

	private static void app(ConfigurableApplicationContext context) {
		StartApplication application = (StartApplication) context.getBean("startApplication");
		application.start();
		application.playMenu();
		application.stop();
		
		
	}
}
