package com.excilys.computerDatabase.back.dataBase.connection;


import org.springframework.stereotype.Component;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class CdbConnection {
	
	private static final String PROP_FILE_NAME = "/datasource.properties";
	HikariDataSource dataSource = new HikariDataSource( new HikariConfig(PROP_FILE_NAME)); 
	
	public HikariDataSource getDataSource() {	
		return dataSource ;
	}

}
