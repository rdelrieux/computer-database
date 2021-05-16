package com.excilys.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class CdbConnection {

	private final static String URL = "jdbc:mysql://localhost:3306/computer-database-db?useSSL=false";
	
	private static CdbConnection instance;

	private String user = "";
	private String password = "";
	private Connection connect;

	private CdbConnection() {
		
	}
	
	
	public static CdbConnection getInstance() {
		if (instance == null) {
			instance = new CdbConnection();
		}
		return instance;
	}

	public void connection(String user, String password) throws SQLException, ClassNotFoundException {

		this.user = user;
		this.password = password;
		//Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(CdbConnection.URL, this.user, this.password);

	}

	public Connection getConnection(String user, String password) throws ClassNotFoundException, SQLException {
		if (connect == null) {
			connection(user, password);
		}
		return connect;
	}
	
	public Connection getConnection() {
		if (connect == null) {
			try {
				connection(user, password);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connect;
	}

}
