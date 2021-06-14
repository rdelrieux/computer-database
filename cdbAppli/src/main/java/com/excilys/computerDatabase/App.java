package com.excilys.computerDatabase;


import com.excilys.computerDatabase.configuration.RootConfig;
import com.excilys.computerDatabase.front.cli.StartApplication;
import com.excilys.computerDatabase.logger.LoggerCdb;

import java.util.function.Predicate;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;





public class App {
	
static int[][] game ;

	public static void main(String[] args) {
		
		//utiliser de l'AOP pour calculer et logger les temps d'exécution des méthodes de vos DAO
		
		LoggerCdb.logDebug("", "start creationRoot methode");

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);	 
	
		
		context.close();
	}



	



	private static void app(ConfigurableApplicationContext context) {
		StartApplication application = (StartApplication) context.getBean("startApplication");
		application.start();
		application.playMenu();
		application.stop();
		
		
	}
}
