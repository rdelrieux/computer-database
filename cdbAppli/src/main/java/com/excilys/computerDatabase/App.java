package com.excilys.computerDatabase;


import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.computerDatabase.back.dataBase.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.back.dataBase.dao.CompanyDAO;
import com.excilys.computerDatabase.back.dataBase.dao.ComputerDAO;
import com.excilys.computerDatabase.back.dataBase.exception.DAOException;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Page;
import com.excilys.computerDatabase.enumeration.OrderBy;
import com.excilys.computerDatabase.front.cli.StartApplication;
import com.excilys.computerDatabase.logger.LoggerCdb;


/**
 * Hello world!
 *
 */
public class App {
	


	public static void main(String[] args) {
	
		
		try {
			CompanyDAO.getInstance().findAll().stream()
			.forEach( s -> System.out.println(s.getName())
			);
	
		}catch(DAOException e ) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
		}
		
		
		try {
			/*CompanyDAO.getInstance().findAll().stream()
					.forEach( s -> System.out.println(s.getName())
					);
			*/
		}catch(DAOException e ) {
			LoggerCdb.logInfo(CompanyDAO.class.getName(), e);
		}
		
		/*
		// Exception return null
		StartApplication application = StartApplication.getInstance();
		application.start();
		application.playMenu();
		application.stop();
*/
	}
}
