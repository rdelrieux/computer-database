package com.excilys.computerDatabase.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = { "com.excilys.computerDatabase.front.servlets"
})
public class ServletConfig extends DelegatingWebMvcConfiguration {
	

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
		    return new CookieLocaleResolver();
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
		    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		    localeChangeInterceptor.setParamName("lang");
		    registry.addInterceptor(localeChangeInterceptor);
		}
	
		
	
		

}
