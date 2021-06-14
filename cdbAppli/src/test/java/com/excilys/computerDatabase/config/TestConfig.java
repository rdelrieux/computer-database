package com.excilys.computerDatabase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
@Configuration

@ComponentScan(basePackages = { 
		"com.excilys.computerDatabase.back.dataBase.binding.mapper",
		"com.excilys.computerDatabase.time",
		"com.excilys.computerDatabase.front.session"
		


		})
//@PropertySource(value = { "cdb.properties" })
//@EnableWebMvc
@EnableAspectJAutoProxy
public class TestConfig {
	
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

	@Bean
	public PlatformTransactionManager getTransactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}

	/*
	@Bean
	public DataSource dataSource() {
	    return new EmbeddedDatabaseBuilder()
	      .setType(EmbeddedDatabaseType.H2)
	      .addScript("classpath:jdbc/schema.sql")
	      .addScript("classpath:jdbc/test-data.sql").build();
	}
*/
}
