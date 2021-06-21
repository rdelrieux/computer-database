package com.excilys.computerDatabase.persistence.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.excilys.computerDatabase.core.logger.LoggerCdb;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
//@EnableAspectJAutoProxy

@ComponentScan(basePackages = {
		
		"com.excilys.computerDatabase.persistence.binding.mapper",
		"com.excilys.computerDatabase.persistence.dao",
		//"com.excilys.computerDatabase.persistence.loggertime"
		
})

public class PersistenceConfig {

	private static final String PROP_HIKARI = "/datasource.properties";

	@Bean
	public HikariDataSource getDataSource() {
		return new HikariDataSource(new HikariConfig(PROP_HIKARI));
	}

	  @Bean
	   public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em 
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(getDataSource());
	      em.setPackagesToScan(new String[] { "com.excilys.computerDatabase.persistence.binding.dto" });

	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(hibernateProperties());

	      return em;
	   }

	

	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		//hibernateProperties.setProperty("c", "create-drop");
		hibernateProperties.setProperty("hibernate.show_sql" , "false");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

		return hibernateProperties;
	}
	
	@Bean
	public PlatformTransactionManager getTransactionManager() {
	    JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());

	    return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	    return new PersistenceExceptionTranslationPostProcessor();
	}


}
