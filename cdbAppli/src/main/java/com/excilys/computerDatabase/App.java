package com.excilys.computerDatabase;


import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.computerDatabase.back.dataBase.binding.mapper.ComputerMapper;
import com.excilys.computerDatabase.back.dataBase.dao.ComputerDAO;
import com.excilys.computerDatabase.back.model.Computer;
import com.excilys.computerDatabase.back.model.Page;
import com.excilys.computerDatabase.front.cli.StartApplication;


/**
 * Hello world!
 *
 */
public class App {
	


	public static void main(String[] args) {
	
		
		// Exception return null
		StartApplication application = StartApplication.getInstance();
		application.start();
		application.playMenu();
		application.stop();

	}
}
