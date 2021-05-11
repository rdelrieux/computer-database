package com.excilys.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.excilys.model.Computer;

public abstract class DAO<T> {

	protected Connection connection = null;

	public DAO(Connection conn) {
		this.connection = conn;
	}

	public abstract ResultSet find(int id);

	public abstract ResultSet findAll();
	
}
