package com.excilys.computerDatabase.web.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.excilys.computerDatabase.core.logger.LoggerCdb;

@Configuration
@ComponentScan(basePackages = { 
		
	
		
		"com.excilys.computerDatabase.web.session",
		"com.excilys.computerDatabase.web.binding.mapper",
		"com.excilys.computerDatabase.web.binding.validateur",
		"com.excilys.computerDatabase.web.controller"
		
		
		
})

public class WebConfig extends WebMvcConfigurationSupport {



	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/static/");

	}

	 @Bean("messageSource")
		public MessageSource getMessageSource() {
		    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		    messageSource.setBasenames("languages/messages");
		    messageSource.setDefaultEncoding("UTF-8");
		    return messageSource;
		}

		@Bean
		public LocaleResolver localeResolver() {
		    SessionLocaleResolver  sessionLocalResolver = new SessionLocaleResolver();
		    sessionLocalResolver.setDefaultLocale(Locale.ENGLISH);
		    return sessionLocalResolver;
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
		    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		    localeChangeInterceptor.setParamName("lang");
		    registry.addInterceptor(localeChangeInterceptor);
		}
	
		
	
		

}
