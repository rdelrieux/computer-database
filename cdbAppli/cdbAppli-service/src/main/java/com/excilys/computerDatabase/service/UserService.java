package com.excilys.computerDatabase.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.persistence.dao.UserDAO;



@Component
public class UserService implements UserDetailsService{

	private UserDAO userDAO;
	
	
	
	public UserService(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDAO.find(username);
	}
	
	
	
	

}
