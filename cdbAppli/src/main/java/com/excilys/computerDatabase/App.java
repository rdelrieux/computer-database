package com.excilys.computerDatabase;


import com.excilys.computerDatabase.configuration.CdbConfiguration;
import com.excilys.computerDatabase.front.cli.StartApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;





public class App {
	
static int[][] game ;

	public static void main(String[] args) {
		
		 ApplicationContext ctx = new AnnotationConfigApplicationContext(CdbConfiguration.class);
		 

		 
		StartApplication application = (StartApplication) ctx.getBean("startApplication");
		application.start();
		application.playMenu();
		application.stop();

	}
}
