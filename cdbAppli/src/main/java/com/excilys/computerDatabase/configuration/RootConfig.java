package com.excilys.computerDatabase.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@ComponentScan(basePackages = { 
		"com.excilys.computerDatabase.front.binding.mapper",
		"com.excilys.computerDatabase.front.binding.validateur",
		"com.excilys.computerDatabase.back.dataBase.dao",
		"com.excilys.computerDatabase.back.dataBase.binding.mapper",
		"com.excilys.computerDatabase.back.service",
		"com.excilys.computerDatabase.front.session"
		})
public class RootConfig  {
	
	private static final String PROP_FILE_NAME = "/datasource.properties";
	
	 @Bean
		public HikariDataSource getDataSource() {
			return new HikariDataSource(new HikariConfig(PROP_FILE_NAME));
	}
	
	 @Bean 
	 public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		 return new NamedParameterJdbcTemplate(getDataSource());
	 }
	
	 @Bean 
	 public JdbcTemplate getJdbcTemplate() {
		 return new JdbcTemplate(getDataSource());
	 }
	 
	 
	
	
	 
}
