package com.excilys.computerDatabase.web.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.excilys.computerDatabase.core.logger.LoggerCdb;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception   {

		auth
	      .inMemoryAuthentication()
	        .withUser("user").password(passwordEncoder().encode("a")).roles("USER")
	    .and()
        .withUser("admin").password(passwordEncoder().encode("a")).roles("ADMIN");

	  }
	
	
    @Bean 
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
           .antMatchers("/resources/**");
    }
    
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	 
    	http
         .csrf().disable()
         .authorizeRequests()
         .mvcMatchers("/" , "/api/**").permitAll()
         .mvcMatchers("/addComputer/**" , "/editComputer/**", "/delete").hasRole("ADMIN")
         .mvcMatchers("/dashboard/**", "/search","/reset" ).authenticated()
         .and()
         .formLogin()
         .loginPage("/login")
         .defaultSuccessUrl("/dashboard", false)
         .failureUrl("/login?error=true")
         .and()
         .logout()
         .logoutUrl("/logout")
         .deleteCookies("JSESSIONID")
         .and()
         .exceptionHandling().accessDeniedPage("/403");
        
    }

}
