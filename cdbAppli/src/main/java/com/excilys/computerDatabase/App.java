package com.excilys.computerDatabase;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.computerDatabase.binding.dto.ComputerDTOInput;
import com.excilys.computerDatabase.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.binding.validater.ComputerValidater;
import com.excilys.computerDatabase.exception.MyException;
import com.excilys.computerDatabase.logger.LoggerCdb;
import com.excilys.computerDatabase.service.ComputerService;
import com.excilys.computerDatabase.servlets.DashBoardServlet;
import com.excilys.computerDatabase.vue.StartApplication;

/**
 * Hello world!
 *
 */
public class App {
	


	public static void main(String[] args) {
		
		ComputerDTOInput ci = new ComputerDTOInput.ComputerDTOInputBuilder
				("null").build();
		try {
			ComputerService.getInstance().addComputer(ci);
		}catch (RuntimeException e){
			LoggerCdb.logError(App.class.getName(), e);

		}
		
		
		
		// Exception return null
		StartApplication application = StartApplication.getInstance();
		application.start();
		application.playMenu();
		application.stop();

	}
}
