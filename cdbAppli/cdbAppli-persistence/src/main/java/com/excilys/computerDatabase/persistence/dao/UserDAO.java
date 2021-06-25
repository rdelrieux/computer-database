package com.excilys.computerDatabase.persistence.dao;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	public UserDetails find(String username) {
		
		
		
		
		return new User("user","a", new ArrayList<>());
	}
	
	
	

}
