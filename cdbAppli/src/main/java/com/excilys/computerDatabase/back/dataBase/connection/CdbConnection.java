package com.excilys.computerDatabase.back.dataBase.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.computerDatabase.logger.LoggerCdb;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class CdbConnection {

	private static final String PROP_FILE_NAME = "datasource.properties";
	
	private static HikariConfig config ;
	private static HikariDataSource ds;
	
	private static CdbConnection instance;
	
	public static CdbConnection getInstance() {
		if (instance == null) {
			instance = new CdbConnection();
		}
		return instance;
	}
	
	private CdbConnection() {
		Properties prop = new Properties();
		InputStream stream = getClass().getClassLoader().getResourceAsStream(PROP_FILE_NAME);
		try {
			prop.load(stream);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		config = new HikariConfig(prop);
		ds = new HikariDataSource(config);
		
	}
	
	

	public Connection getConnection() throws SQLException{	
			return ds.getConnection();
	}

	

}
