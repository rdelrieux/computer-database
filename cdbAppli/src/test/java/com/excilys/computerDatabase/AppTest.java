package com.excilys.computerDatabase;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computerDatabase.config.TestConfig;
import com.excilys.computerDatabase.front.session.Session;
import com.excilys.computerDatabase.logger.LoggerCdb;
import com.excilys.computerDatabase.time.DAO;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	
	@Test
	public void testApp() {
		
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);	
		DAO dao = context.getBean(DAO.class);
		dao.search(new Session()).stream().forEach(e ->  LoggerCdb.logDebug(e.toString()) );
		context.close();

	}


	

}


