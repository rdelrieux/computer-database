package com.excilys.computerDatabase.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.excilys.computerDatabase.logger.LoggerCdb;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { 
		"com.excilys.computerDatabase.front.binding.mapper",
		"com.excilys.computerDatabase.front.binding.validateur",
		"com.excilys.computerDatabase.back.dataBase.dao",
		"com.excilys.computerDatabase.back.dataBase.binding.mapper",
		"com.excilys.computerDatabase.back.service"


		})

public class WebConfig {/*

public class WebConfig implements WebMvcConfigurer, WebApplicationInitializer {

	private static final String PROP_FILE_NAME = "/datasource.properties";

	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		LoggerCdb.logInfo("", "on startup start");
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebConfig.class);
		//context.setServletContext(servletContext);

		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		LoggerCdb.logInfo("", "on startup end");
	}
	
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/jsp/");
		bean.setSuffix(".jsp");
		return bean;
	}

	
	@Bean
	public HikariDataSource getDataSource() {
		return new HikariDataSource(new HikariConfig(PROP_FILE_NAME));
	}
	
	 @Override
	    public void configureDefaultServletHandling(
	      DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
	
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/resources/**")
        .addResourceLocations("/resources/static/");
		 
	 }
	
	
	
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
