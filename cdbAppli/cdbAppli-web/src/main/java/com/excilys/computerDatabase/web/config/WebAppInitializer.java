package com.excilys.computerDatabase.web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.excilys.computerDatabase.core.config.RootConfig;
import com.excilys.computerDatabase.web.config.security.SecurityConfig;


public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	 

	@Override
	protected Class<?>[] getRootConfigClasses() {		
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return  new Class<?>[] {RootConfig.class ,SecurityConfig.class, WebConfig.class  };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	  

	
}
