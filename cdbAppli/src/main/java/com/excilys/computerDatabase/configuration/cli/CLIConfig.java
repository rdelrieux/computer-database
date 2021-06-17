package com.excilys.computerDatabase.configuration.cli;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {

		"com.excilys.computerDatabase.front.controleurCLI",
		"com.excilys.computerDatabase.front.cli" 
		
})
public class CLIConfig  {

	
}