package com.excilys.computerDatabase.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { 
		"com.excilys.computerDatabase.front.servlets"
})
public class ServletConfig extends DelegatingWebMvcConfiguration  {
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 
		 registry.addResourceHandler("/resources/**")
         .addResourceLocations("/resources/");
		 
	    }

}
