package com.excilys.computerDatabase.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
//@PropertySource(value = { "cdb.properties" })
@ComponentScan(basePackages = { "com.excilys.computerDatabase.back.dataBase.connection",
		"com.excilys.computerDatabase.back.dataBase.dao",
		"com.excilys.computerDatabase.back.dataBase.binding.mapper",
		"com.excilys.computerDatabase.back.service",

		"com.excilys.computerDatabase.front.controleurCLI",
		"com.excilys.computerDatabase.front.binding.mapper",
		"com.excilys.computerDatabase.front.binding.validateur",
		"com.excilys.computerDatabase.front.cli" })
public class RootConfig  {

	
}
