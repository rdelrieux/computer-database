package com.excilys.computerDatabase.persistence.binding.dto.security;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.excilys.computerDatabase.core.security.Role;

@Entity
@Table(name="role")
public class RoleEntity {
	
	@Id
	private int id; 
	
	@Enumerated(EnumType.STRING)
	private Role role;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", Role=" + role + "]";
	} 
	

}
