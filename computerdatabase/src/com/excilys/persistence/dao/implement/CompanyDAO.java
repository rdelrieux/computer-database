package com.excilys.persistence.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapping.CompanyMapping;
import com.excilys.model.Company;
import com.excilys.persistence.dao.DAO;
import com.excilys.service.CompanyService;

public class CompanyDAO extends DAO<Company>{

	private static final String REQUET_AFFICHER_TOUTE_COMPANIES = "SELECT * FROM company";
	private static final String REQUET_TROUVER_COMPANY_FROM_ID = "SELECT * FROM company WHERE id = ?";
	private static final String REQUET_TROUVER_COMPANY_FROM_NAME = "SELECT * FROM company WHERE name = ?";

	private static CompanyDAO instance ;	
	private CompanyMapping companyMapping;
	
	private  CompanyDAO(Connection conn) {
		super(conn);
		companyMapping = CompanyMapping.getInstance();
		}

	
	public static CompanyDAO getInstance(Connection conn)  {
		if (instance == null) {
			instance = new CompanyDAO(conn);
		}
		return instance;
	}
	
	
	public Company find(String name) {
		Company company = new Company(); 
		
		 try {
			//this.connection.setAutoCommit(false);
			PreparedStatement statementFind = this.connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_NAME);
			statementFind.setString(1, name);
			ResultSet result = statementFind.executeQuery();
			company = this.companyMapping.toCompany(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return company;
	}
	
	
	
	@Override
	public Company find(int id) {
		Company company = new Company(); 
		
		 try {
			//this.connection.setAutoCommit(false);
			
			PreparedStatement statementFind = this.connection.prepareStatement(REQUET_TROUVER_COMPANY_FROM_ID);
			statementFind.setInt(1, id);
			ResultSet result = statementFind.executeQuery();
			company = this.companyMapping.toCompany(result);
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return company;
	}
	
	@Override
	public  List<Company> findAll() {
		List<Company> listCompany = new ArrayList<Company>();
		try {
				//this.connection.setAutoCommit(false);
				PreparedStatement statementFind = this.connection.prepareStatement(REQUET_AFFICHER_TOUTE_COMPANIES);
				ResultSet result = statementFind.executeQuery();
				listCompany = this.companyMapping.toListCompany(result);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return  listCompany;
	}
	
	
	
	
	

}
