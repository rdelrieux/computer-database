package com.excilys.persistence.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Company;
import com.excilys.persistence.dao.DAO;

public class CompanyDAO extends DAO{

	public CompanyDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Company find(int id) {
		Company company = new Company(); 
		
		 try {
			//this.connection.setAutoCommit(false);
			
			PreparedStatement statementFind = this.connection.prepareStatement("SELECT * FROM company WHERE id = ?");
			statementFind.setInt(1, id);
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
	public List<Company> findAll() {
		ArrayList<Company> res = new ArrayList<Company>();
		
		 try {
				//this.connection.setAutoCommit(false);
				PreparedStatement statementFind = this.connection.prepareStatement("SELECT * FROM company");
				ResultSet result = statementFind.executeQuery();
				
				while(result.next()) {
					Company company = new Company(result.getInt("id"), result.getString("name") );
					res.add(company);		
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return  res;
	}
	
	
	
	
	

}
