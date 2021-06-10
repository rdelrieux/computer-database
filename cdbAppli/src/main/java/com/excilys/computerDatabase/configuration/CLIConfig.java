package com.excilys.computerDatabase.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
//@PropertySource(value = { "cdb.properties" })
@ComponentScan(basePackages = {
		"com.excilys.computerDatabase.back.dataBase.dao",
		"com.excilys.computerDatabase.back.dataBase.binding.mapper",
		"com.excilys.computerDatabase.back.service",

		"com.excilys.computerDatabase.front.controleurCLI",
		"com.excilys.computerDatabase.front.binding.mapper",
		"com.excilys.computerDatabase.front.binding.validateur",
		"com.excilys.computerDatabase.front.cli" })
public class CLIConfig  {

	private static final String PROP_FILE_NAME = "/datasource.properties";
	
	 @Bean
		public HikariDataSource getDataSource() {
			return new HikariDataSource(new HikariConfig(PROP_FILE_NAME));
	}
	
}