package com.excilys.computerDatabase.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class CdbConnection {

	private final static String URL = "jdbc:mysql://localhost:3306/computer-database-db?useSSL=false";
	
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
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(CdbConnection.URL, this.user, this.password);
		} catch (SQLException 
				| ClassNotFoundException e
				) {
			e.printStackTrace();
		}
		return null; 	

	}

	

}
