package com.excilys.computerDatabase.back.dataBase.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.back.dataBase.exception.ConnectionException;
import com.excilys.computerDatabase.logger.LoggerCdb;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class CdbConnection {

	
	private static final String PROP_FILE_NAME = "datasource.properties";
	
	private static HikariConfig config ;
	private static HikariDataSource ds;
	
	
	public CdbConnection() {
		Properties prop = new Properties();
		InputStream stream = getClass().getClassLoader().getResourceAsStream(PROP_FILE_NAME);
		try {
			prop.load(stream);
			
		} catch (IOException e) {
			LoggerCdb.logError(CdbConnection.class.toString(), e);
			throw new ConnectionException();
		}
		
		config = new HikariConfig(prop);
		ds = new HikariDataSource(config);
		
	}
	
	
	public Connection getConnection() {	
			
			try {
				return ds.getConnection();
			} catch (SQLException e) {
				LoggerCdb.logError(CdbConnection.class.toString(), e);
				throw new ConnectionException();
			}
	}

	

}
