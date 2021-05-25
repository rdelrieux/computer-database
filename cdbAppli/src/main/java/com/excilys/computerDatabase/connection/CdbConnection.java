package com.excilys.computerDatabase.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.excilys.computerDatabase.logger.LoggerCdb;


public class CdbConnection {

	private final static String URL = "jdbc:mysql://localhost:3306/computer-database-db?useSSL=false";
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static CdbConnection instance;

	private String user  = "admincdb";
	private String password = "qwerty1234";
	
	
	private CdbConnection() {
		
	}
	
	public static CdbConnection getInstance() {
		if (instance == null) {
			instance = new CdbConnection();
		}
		return instance;
	}

	public Connection getConnection(){	
		
			try {
				Class.forName(DRIVER);
				//LoggerCdb.logInfo(CdbConnection.class.getName(), "Driver Loaded");
				return DriverManager.getConnection(CdbConnection.URL, this.user, this.password);
			} catch (ClassNotFoundException e) {
				LoggerCdb.logFatal(CdbConnection.class.getName(), e);
			} catch (SQLException e) {
				LoggerCdb.logFatal(CdbConnection.class.getName(), e);
			}
			
				
		return null; 	

	}

	

}
