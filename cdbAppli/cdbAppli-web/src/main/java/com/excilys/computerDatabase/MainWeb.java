package com.excilys.computerDatabase;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.excilys.computerDatabase.core.config.RootConfig;
import com.excilys.computerDatabase.core.logger.LoggerCdb;
import com.excilys.computerDatabase.core.security.Role;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.web.session.Session;

public class MainWeb {
	
	public static void main(String[] args) {
		
		LoggerCdb.logInfo("", "start app");
		
		System.out.println( Role.ADMIN );
		
		//start();s
		
	}
	
	private static void start() {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);	 
		
		//@PreAuthorize("hasAuthority('Admin')")  
		//@PreAuthorize("hasAuthority('User')")  
		//@PreAuthorize("permitAll()")  
		//@PreAuthorize("isAuthenticated()")  
		
		
		// security database + encodage 
		// lambda ::
		//responce entity vs requesbody
		
		ComputerService serviceComputer = context.getBean(ComputerService.class);
		CompanyService serviceCompany = context.getBean(CompanyService.class);
		
		Session s = new Session ();
		String search = "Mac";
		serviceCompany.findAll().forEach(c -> System.out.println(c));
		
		System.out.println( );

		context.close();
		
		
		
	}
	
	

}
