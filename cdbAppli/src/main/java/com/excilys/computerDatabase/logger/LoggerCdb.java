package com.excilys.computerDatabase.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggerCdb {
	private static LoggerCdb instance;

	private LoggerCdb() {
		
	}
	
	public static LoggerCdb getInstance() {
		if (instance == null) {
			instance = new LoggerCdb();
		}
		return instance;
	}
	public static void logInfo(String name, String message) {
		Logger logger = LogManager.getLogger(name);
		logger.info( message);
	}
	
	public static void logInfo(String name, Exception exception) {
		Logger logger = LogManager.getLogger(name);
		logger.info(exception.getClass() + " : " + exception.getMessage());
		//exception.printStackTrace();
	}
	
	public static void logWarn(String name, Exception exception) {
		Logger logger = LogManager.getLogger(name);
		logger.warn(exception.getClass() + " : " + exception.getMessage());
		exception.printStackTrace();
	}
	
	public static void logError(String name, Exception exception) {
		Logger logger = LogManager.getLogger(name);
		logger.error(exception.getClass() + " : " + exception.getMessage());
		exception.printStackTrace();
	}
	
	public static void logFatal(String name, Exception exception) {
		Logger logger = LogManager.getLogger(name);
		logger.fatal(exception.getClass() + " : " + exception.getMessage());
		exception.printStackTrace();
	}

	public static void logError(String name, Throwable e) {
		Logger logger = LogManager.getLogger(name);
		logger.fatal(e.getClass() + " : " + e.getMessage());
		e.printStackTrace();
		
	}
	
}
