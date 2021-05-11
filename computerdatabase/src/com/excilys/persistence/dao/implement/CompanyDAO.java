package com.excilys.persistence.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Company;
import com.excilys.persistence.dao.DAO;

public class CompanyDAO extends DAO<Company>{

	protected static final String REQUET_AFFICHER_TOUTE_COMPANIES = "SELECT * FROM company";

	
	public CompanyDAO(Connection conn) {
		super(conn);
	}

	public Company find(String name) {
		Company company = new Company(); 
		
		 try {
			//this.connection.setAutoCommit(false);
			PreparedStatement statementFind = this.connection.prepareStatement("SELECT * FROM company WHERE name = ?");
			statementFind.setString(1, name);
			ResultSet result = statementFind.executeQuery();
			
			if(result.first()) {
				company = new Company(result.getInt("id"), result.getString("name") );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return company;
	}
	
	
	
	@Override
	public ResultSet find(int id) {
		Company company = new Company(); 
		
		 try {
			//this.connection.setAutoCommit(false);
			
			PreparedStatement statementFind = this.connection.prepareStatement("SELECT * FROM company WHERE id = ?");
			statementFind.setInt(1, id);
			ResultSet result = statementFind.executeQuery();
			
			if(result.first()) {
				company = new Company(result.getInt("id"), result.getString("name") );
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return null;
	}
	
	@Override
	public  ResultSet findAll() {
		 try {
				//this.connection.setAutoCommit(false);
				PreparedStatement statementFind = this.connection.prepareStatement(REQUET_AFFICHER_TOUTE_COMPANIES);
				ResultSet result = statementFind.executeQuery();
				
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return  null;
	}
	
	
	
	
	

}
