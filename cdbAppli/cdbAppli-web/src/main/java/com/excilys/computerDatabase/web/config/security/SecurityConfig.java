package com.excilys.computerDatabase.web.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.excilys.computerDatabase.core.logger.LoggerCdb;
import com.excilys.computerDatabase.service.UserService;
import com.excilys.computerDatabase.web.config.security.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserService userService;
	private JwtRequestFilter jwtRequestFilter;

	

	public SecurityConfig(UserService userService, JwtRequestFilter jwtRequestFilter) {
		super();
		this.userService = userService;
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userService);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		// return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		
		
		http.csrf().disable()
				.authorizeRequests()
				.mvcMatchers("/api/login").permitAll()
				.mvcMatchers("/addComputer/**", "/editComputer/**", "/delete").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		/*
		 * .and() .formLogin() .loginPage("/login") .defaultSuccessUrl("/dashboard",
		 * false) .failureUrl("/login?error=true") .and() .logout()
		 * .logoutUrl("/logout") .deleteCookies("JSESSIONID") .and()
		 * .exceptionHandling().accessDeniedPage("/403");
		 */
	}

}
