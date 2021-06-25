package com.excilys.computerDatabase.persistence.binding.dto.security;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.excilys.computerDatabase.core.security.Role;

@Entity
public class RoleEntity {
	
	@Id
	private int id; 
	
	@Enumerated(EnumType.STRING)
	private Role Role; 
	
	
	

}
