package com.excilys.computerDatabase;


import com.excilys.computerDatabase.configuration.CLIConfig;
import com.excilys.computerDatabase.front.cli.StartApplication;
import com.excilys.computerDatabase.logger.LoggerCdb;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;





public class App {
	
static int[][] game ;

	public static void main(String[] args) {
		LoggerCdb.logDebug("", "start creationRoot methode");

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(CLIConfig.class);	 
		StartApplication application = (StartApplication) context.getBean("startApplication");
		application.start();
		application.playMenu();
		application.stop();
		context.close();

	}
}
