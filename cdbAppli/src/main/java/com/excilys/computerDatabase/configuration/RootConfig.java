package com.excilys.computerDatabase.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { 
		"com.excilys.computerDatabase.configuration.persistence",
		
		"com.excilys.computerDatabase.front.session",
		"com.excilys.computerDatabase.front.binding.mapper",
		"com.excilys.computerDatabase.front.binding.validateur",
	
		"com.excilys.computerDatabase.back.dataBase.dao",
		"com.excilys.computerDatabase.back.dataBase.binding.mapper",
		
		"com.excilys.computerDatabase.back.service"
		
		
		
})


public class RootConfig {

	
}
