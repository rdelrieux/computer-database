package com.excilys.computerDatabase.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { 
		"com.excilys.computerDatabase.core.logger",
		
		"com.excilys.computerDatabase.persistence.config",

		"com.excilys.computerDatabase.service"
		
})

public class RootConfig {
		
}
