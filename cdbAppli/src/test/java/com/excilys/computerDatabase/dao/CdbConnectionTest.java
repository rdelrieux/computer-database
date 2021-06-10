package com.excilys.computerDatabase.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.computerDatabase.logger.LoggerCdb;

public class CdbConnectionTest {
	
	private static final String PROP_FILE_NAME = "databaseTest.properties";
	private final static String PROPERTY_URL = "database.url";
	private final static String PROPERTY_DRIVER = "database.driver";
	private static final String PROPERTY_USER = "database.user";
	private static final String PROPERTY_PASSWORD = "database.password";
	
	private static CdbConnectionTest instance;

	private String user  ;
	private String password ;
	private String url ;
	private String driver;
	
	private CdbConnectionTest() {
		Properties prop = new Properties();
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream(PROP_FILE_NAME);
		try {
			prop.load(stream);
			this.driver = prop.getProperty(PROPERTY_DRIVER);
			this.url = prop.getProperty(PROPERTY_URL);
			this.user = prop.getProperty(PROPERTY_USER);
			this.password = prop.getProperty(PROPERTY_PASSWORD);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static CdbConnectionTest getInstance() {
		if (instance == null) {
			instance = new CdbConnectionTest();
		}
		return instance;
	}

	public Connection getConnection(){	
		
			try {
				try {
					Class.forName(this.driver);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//LoggerCdb.logInfo(CdbConnection.class.getName(), "Driver Loaded");
				return DriverManager.getConnection(this.url, this.user, this.password);
			} /*catch (//ClassNotFoundException e) {
				LoggerCdb.logFatal(CdbConnection.class.getName(), e);
			} */catch (SQLException e) {
				LoggerCdb.logFatal(CdbConnectionTest.class.getName(), e);
			}
			
				
		return null; 	

	}


}
